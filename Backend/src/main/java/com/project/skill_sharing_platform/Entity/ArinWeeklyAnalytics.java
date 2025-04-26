package com.project.skill_sharing_platform.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "arin_weekly_analytics")
public class ArinWeeklyAnalytics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime weekStartDate;
    private Integer skillsAdded;
    private Integer skillsUpdated;
    private Integer followersGained;
    private Integer followingAdded;
    private Integer badgesEarned;
    private Double engagementScore; // Calculated based on various activities
    private String weeklyHighlights; // JSON string containing notable achievements
    private String skillRecommendations; // JSON string containing skill suggestions

    @PrePersist
    protected void onCreate() {
        if (weekStartDate == null) {
            weekStartDate = LocalDateTime.now();
        }
    }
}
