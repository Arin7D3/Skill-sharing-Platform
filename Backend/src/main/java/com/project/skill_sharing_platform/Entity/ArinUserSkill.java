package com.project.skill_sharing_platform.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "arin_user_skills")
public class ArinUserSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String skillName;
    private Integer proficiencyLevel; // 1-5 scale
    private LocalDateTime addedDate;
    private LocalDateTime lastUpdated;
    private String description;
    private Boolean isVerified;

    @PrePersist
    protected void onCreate() {
        addedDate = LocalDateTime.now();
        lastUpdated = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdated = LocalDateTime.now();
    }
}
