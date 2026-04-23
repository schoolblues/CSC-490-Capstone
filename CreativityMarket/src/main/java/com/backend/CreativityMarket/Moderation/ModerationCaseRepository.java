package com.backend.CreativityMarket.Moderation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ModerationCaseRepository extends JpaRepository<ModerationCase, Long> {

    List<ModerationCase> findByStatus(ModerationStatus status);

    List<ModerationCase> findByTargetType(ModerationTargetType targetType);

    List<ModerationCase> findAllByOrderByPriorityDescCreatedAtAsc();

    long countByStatus(ModerationStatus status);

    @Query("""
        SELECT m.status, COUNT(m)
        FROM ModerationCase m
        GROUP BY m.status
    """)
    List<Object[]> countCasesByStatus();
}