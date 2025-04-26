package com.project.skill_sharing_platform.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Data
@Table(name = "arin_users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    
    @Column(unique = true)
    private String email;
    
    private String fullName;
    private String password;
    private String profilePicture;
    private String provider; // OAuth2 provider (google, github, etc)
    private String providerId; // OAuth2 provider user id
    
    @ElementCollection
    @Builder.Default
    private Set<String> roles = new HashSet<>();
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<ArinUserSkill> skills = new ArrayList<>();
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<ArinUserBadge> badges = new ArrayList<>();
    
    @ManyToMany
    @JoinTable(
        name = "arin_user_followers",
        joinColumns = @JoinColumn(name = "following_id"),
        inverseJoinColumns = @JoinColumn(name = "follower_id")
    )
    @Builder.Default
    private Set<User> followers = new HashSet<>();
    
    @ManyToMany(mappedBy = "followers")
    @Builder.Default
    private Set<User> following = new HashSet<>();
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<ArinWeeklyAnalytics> weeklyAnalytics = new ArrayList<>();
}

