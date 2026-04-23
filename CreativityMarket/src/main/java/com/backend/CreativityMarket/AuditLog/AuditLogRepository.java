package com.backend.CreativityMarket.AuditLog;

import com.backend.CreativityMarket.Common.EntityType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

    // logs by admin
    List<AuditLog> findByPerformedBy(Long performedBy);

    // logs by action type
    List<AuditLog> findByAction(AuditAction action);

    // logs by entity type
    List<AuditLog> findByTargetEntity(EntityType targetEntity);

    // logs by time range
    List<AuditLog> findByTimestampBetween(LocalDateTime from, LocalDateTime to);

    List<AuditLog> findByOrderByTimestampDesc();

    @Query("""
        SELECT FUNCTION('DATE', l.timestamp), COUNT(l)
        FROM AuditLog l
        GROUP BY FUNCTION('DATE', l.timestamp)
        ORDER BY FUNCTION('DATE', l.timestamp)
    """)
    List<Object[]> countActionsPerDay();


    @Query("""
        SELECT l.action, COUNT(l)
        FROM AuditLog l
        GROUP BY l.action
        ORDER BY COUNT(l) DESC
    """)
    List<Object[]> countByAction();
}