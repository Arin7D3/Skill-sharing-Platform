package com.project.skill_sharing_platform.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "arin_user_badges")
public class ArinUserBadge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String badgeName;
    private String description;
    private LocalDateTime earnedDate;
    private String category; // e.g., "ENGAGEMENT", "SKILL_MASTERY", "MENTOR"
    private Integer level; // For tiered badges (e.g., Bronze, Silver, Gold)
    private String imageUrl;

    @PrePersist
    protected void onCreate() {
        earnedDate = LocalDateTime.now();
    }
}
