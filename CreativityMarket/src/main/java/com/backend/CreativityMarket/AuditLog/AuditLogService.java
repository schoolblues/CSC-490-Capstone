package com.backend.CreativityMarket.AuditLog;

import com.backend.CreativityMarket.Common.EntityType;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuditLogService {
    
    private final AuditLogRepository auditLogRepository;

    //retrive all logs
    public List<AuditLog> getAllLogs() {
        return auditLogRepository.findAll();
    }

    //retrive logs for specific admin
    public List<AuditLog> getLogsByAdmin(Long adminId) {
        return auditLogRepository.findByPerformedBy(adminId);
    }

    //retrive logs for a specific entity type
    public List<AuditLog> getLogsByEntity(EntityType targetEntity) {
        return auditLogRepository.findByTargetEntity(targetEntity);
    }

    public List<AuditLog> getLogsByAction(AuditAction action) {
        return auditLogRepository.findByAction(action);
    }

    public List<AuditLog> getLogsByDateRange(LocalDateTime from, LocalDateTime to) {
        return auditLogRepository.findByTimestampBetween(from, to);
    }

    public List<AuditLog> getFilteredLogs(Long adminId,
                                          AuditAction action, 
                                          EntityType targetEntity, 
                                          LocalDateTime from, 
                                          LocalDateTime to) {

        return auditLogRepository.findByOrderByTimestampDesc().stream()
                .filter(log -> (adminId == null || log.getPerformedBy().equals(adminId)))
                .filter(log -> (targetEntity == null || log.getTargetEntity() == targetEntity))
                .filter(log -> (action == null || log.getAction() == action))
                .filter(log -> (from == null || !log.getTimestamp().isBefore(from)))
                .filter(log -> (to == null || !log.getTimestamp().isAfter(to)))
                .toList();
    }

    @Transactional
    public void logAction(Long performedBy,
                            AuditAction action,
                            EntityType targetEntity,
                            Long targetId) {
        AuditLog logEntry = new AuditLog();
    
        logEntry.setPerformedBy(performedBy);
        logEntry.setAction(action);
        logEntry.setTargetEntity(targetEntity);
        logEntry.setTargetId(targetId);
        logEntry.setTimestamp(LocalDateTime.now());
        auditLogRepository.save(logEntry);
    }
}
