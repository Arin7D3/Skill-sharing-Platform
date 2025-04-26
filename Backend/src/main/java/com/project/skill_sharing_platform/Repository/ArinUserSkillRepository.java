package com.project.skill_sharing_platform.Repository;

import com.project.skill_sharing_platform.Entity.ArinUserSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArinUserSkillRepository extends JpaRepository<ArinUserSkill, Long> {
}
