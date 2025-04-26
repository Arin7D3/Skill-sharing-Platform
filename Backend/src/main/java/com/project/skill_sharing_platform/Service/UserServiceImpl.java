package com.project.skill_sharing_platform.Service;

import com.project.skill_sharing_platform.Entity.*;
import com.project.skill_sharing_platform.dto.*;
import com.project.skill_sharing_platform.Repository.*;
import com.project.skill_sharing_platform.Exception.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User register(UserDTO userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setFullName(userDto.getFullName());
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, UserDTO userDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setEmail(userDto.getEmail());
        user.setFullName(userDto.getFullName());
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) throw new UserNotFoundException("User not found");
        userRepository.deleteById(id);
    }

    @Override
    public ArinUserSkill addSkill(Long userId, ArinUserSkillDTO skillDTO) {
        User user = getUserById(userId);
        
        ArinUserSkill skill = ArinUserSkill.builder()
            .user(user)
            .skillName(skillDTO.getSkillName())
            .proficiencyLevel(skillDTO.getProficiencyLevel())
            .description(skillDTO.getDescription())
            .isVerified(false)
            .build();

        user.getSkills().add(skill);
        userRepository.save(user);
        return skill;
    }

    @Override
    public List<ArinUserSkill> getUserSkills(Long userId) {
        User user = getUserById(userId);
        return user.getSkills();
    }

    @Override
    public List<ArinUserBadge> getUserBadges(Long userId) {
        User user = getUserById(userId);
        return user.getBadges();
    }

    @Override
    public ArinWeeklyAnalytics getCurrentWeekAnalytics(Long userId) {
        User user = getUserById(userId);
        LocalDateTime now = LocalDateTime.now();
        
        return user.getWeeklyAnalytics().stream()
            .filter(analytics -> analytics.getWeekStartDate().isAfter(now.minusDays(7)))
            .findFirst()
            .orElseGet(() -> {
                ArinWeeklyAnalytics newAnalytics = ArinWeeklyAnalytics.builder()
                    .user(user)
                    .weekStartDate(now)
                    .skillsAdded(0)
                    .skillsUpdated(0)
                    .followersGained(0)
                    .followingAdded(0)
                    .badgesEarned(0)
                    .engagementScore(0.0)
                    .build();
                user.getWeeklyAnalytics().add(newAnalytics);
                userRepository.save(user);
                return newAnalytics;
            });
    }

    @Override
    public List<ArinWeeklyAnalytics> getAnalyticsHistory(Long userId, Integer weeks) {
        User user = getUserById(userId);
        LocalDateTime cutoff = LocalDateTime.now().minusWeeks(weeks);
        
        return user.getWeeklyAnalytics().stream()
            .filter(analytics -> analytics.getWeekStartDate().isAfter(cutoff))
            .sorted(Comparator.comparing(ArinWeeklyAnalytics::getWeekStartDate).reversed())
            .collect(Collectors.toList());
    }

    @Override
    public void followUser(Long userId, Long targetUserId) {
        if (userId.equals(targetUserId)) {
            throw new IllegalArgumentException("Users cannot follow themselves");
        }

        User follower = getUserById(userId);
        User target = getUserById(targetUserId);

        follower.getFollowing().add(target);
        target.getFollowers().add(follower);

        userRepository.save(follower);
        userRepository.save(target);

        // Update analytics
        updateFollowAnalytics(follower, target);
    }

    @Override
    public void unfollowUser(Long userId, Long targetUserId) {
        User follower = getUserById(userId);
        User target = getUserById(targetUserId);

        follower.getFollowing().remove(target);
        target.getFollowers().remove(follower);

        userRepository.save(follower);
        userRepository.save(target);
    }

    @Override
    public Set<User> getFollowers(Long userId) {
        return getUserById(userId).getFollowers();
    }

    @Override
    public Set<User> getFollowing(Long userId) {
        return getUserById(userId).getFollowing();
    }

    @Override
    public List<SkillRecommendationDTO> getSkillRecommendations(Long userId) {
        User user = getUserById(userId);
        Set<String> userSkills = user.getSkills().stream()
            .map(ArinUserSkill::getSkillName)
            .collect(Collectors.toSet());

        // Get skills from users they follow
        Set<ArinUserSkill> followingSkills = user.getFollowing().stream()
            .flatMap(following -> following.getSkills().stream())
            .collect(Collectors.toSet());

        // Create recommendations based on following's skills
        return followingSkills.stream()
            .filter(skill -> !userSkills.contains(skill.getSkillName()))
            .map(skill -> SkillRecommendationDTO.builder()
                .skillName(skill.getSkillName())
                .reason("Popular among people you follow")
                .relevanceScore(calculateRelevanceScore(skill, user))
                .numberOfUsersWithSkill((int) user.getFollowing().stream()
                    .filter(f -> f.getSkills().stream()
                        .anyMatch(s -> s.getSkillName().equals(skill.getSkillName())))
                    .count())
                .category("FOLLOWING")
                .build())
            .sorted(Comparator.comparing(SkillRecommendationDTO::getRelevanceScore).reversed())
            .limit(5)
            .collect(Collectors.toList());
    }

    private void updateFollowAnalytics(User follower, User target) {
        ArinWeeklyAnalytics followerAnalytics = getCurrentWeekAnalytics(follower.getId());
        followerAnalytics.setFollowingAdded(followerAnalytics.getFollowingAdded() + 1);

        ArinWeeklyAnalytics targetAnalytics = getCurrentWeekAnalytics(target.getId());
        targetAnalytics.setFollowersGained(targetAnalytics.getFollowersGained() + 1);
    }

    private Double calculateRelevanceScore(ArinUserSkill skill, User user) {
        double baseScore = skill.getProficiencyLevel() * 0.4;
        double popularityScore = user.getFollowing().stream()
            .filter(f -> f.getSkills().stream()
                .anyMatch(s -> s.getSkillName().equals(skill.getSkillName())))
            .count() * 0.6;

        return baseScore + popularityScore;
    }
}
 {
    
}
