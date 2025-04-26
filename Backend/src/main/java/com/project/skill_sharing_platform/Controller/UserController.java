package com.project.skill_sharing_platform.Controller;

import com.project.skill_sharing_platform.Service.UserService;
import com.project.skill_sharing_platform.Entity.*;
import com.project.skill_sharing_platform.dto.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserDTO userDto) {
        return ResponseEntity.ok(userService.register(userDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody UserDTO userDto) {
        return ResponseEntity.ok(userService.updateUser(id, userDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    // Skills management
    @PostMapping("/{userId}/skills")
    public ResponseEntity<ArinUserSkill> addSkill(
            @PathVariable Long userId,
            @RequestBody ArinUserSkillDTO skillDTO) {
        return ResponseEntity.ok(userService.addSkill(userId, skillDTO));
    }

    @GetMapping("/{userId}/skills")
    public ResponseEntity<List<ArinUserSkill>> getUserSkills(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserSkills(userId));
    }

    // Badge management
    @GetMapping("/{userId}/badges")
    public ResponseEntity<List<ArinUserBadge>> getUserBadges(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserBadges(userId));
    }

    // Analytics
    @GetMapping("/{userId}/analytics/weekly")
    public ResponseEntity<ArinWeeklyAnalytics> getCurrentWeekAnalytics(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getCurrentWeekAnalytics(userId));
    }

    @GetMapping("/{userId}/analytics/history")
    public ResponseEntity<List<ArinWeeklyAnalytics>> getAnalyticsHistory(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "4") Integer weeks) {
        return ResponseEntity.ok(userService.getAnalyticsHistory(userId, weeks));
    }

    // Following system
    @PostMapping("/{userId}/follow/{targetUserId}")
    public ResponseEntity<Void> followUser(
            @PathVariable Long userId,
            @PathVariable Long targetUserId) {
        userService.followUser(userId, targetUserId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}/unfollow/{targetUserId}")
    public ResponseEntity<Void> unfollowUser(
            @PathVariable Long userId,
            @PathVariable Long targetUserId) {
        userService.unfollowUser(userId, targetUserId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/followers")
    public ResponseEntity<Set<User>> getFollowers(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getFollowers(userId));
    }

    @GetMapping("/{userId}/following")
    public ResponseEntity<Set<User>> getFollowing(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getFollowing(userId));
    }

    // Skill recommendations
    @GetMapping("/{userId}/recommendations")
    public ResponseEntity<List<SkillRecommendationDTO>> getSkillRecommendations(
            @PathVariable Long userId) {
        return ResponseEntity.ok(userService.getSkillRecommendations(userId));
    }
}

