package com.project.skill_sharing_platform.dto;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class SkillRecommendationDTO {
    private String skillName;
    private String reason;
    private Double relevanceScore;
    private Integer numberOfUsersWithSkill;
    private String category;
}
