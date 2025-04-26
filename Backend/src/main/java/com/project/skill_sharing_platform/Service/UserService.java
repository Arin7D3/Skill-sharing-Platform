package com.project.skill_sharing_platform.Service;

import com.project.skill_sharing_platform.Entity.*;
import com.project.skill_sharing_platform.dto.*;

import java.util.List;
import java.util.Set;

public interface UserService {
    // Basic CRUD operations
    User register(UserDTO userDto);
    User updateUser(Long id, UserDTO userDto);
    User getUserById(Long id);
    List<User> getAllUsers();
    void deleteUser(Long id);

    // Skills management
    ArinUserSkill addSkill(Long userId, ArinUserSkillDTO skillDTO);
    List<ArinUserSkill> getUserSkills(Long userId);

    // Badge management
    List<ArinUserBadge> getUserBadges(Long userId);

    // Analytics
    ArinWeeklyAnalytics getCurrentWeekAnalytics(Long userId);
    List<ArinWeeklyAnalytics> getAnalyticsHistory(Long userId, Integer weeks);

    // Following system
    void followUser(Long userId, Long targetUserId);
    void unfollowUser(Long userId, Long targetUserId);
    Set<User> getFollowers(Long userId);
    Set<User> getFollowing(Long userId);

    // Recommendations
    List<SkillRecommendationDTO> getSkillRecommendations(Long userId);
}

