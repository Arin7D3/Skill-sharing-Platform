package com.project.skill_sharing_platform.Repository;

import com.project.skill_sharing_platform.Entity.ArinUserBadge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArinUserBadgeRepository extends JpaRepository<ArinUserBadge, Long> {
}
