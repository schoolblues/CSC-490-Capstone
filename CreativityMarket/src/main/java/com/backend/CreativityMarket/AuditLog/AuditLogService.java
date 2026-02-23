package com.backend.CreativityMarket.AuditLog;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
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
        return auditLogRepository.findAll()
                .stream()
                .filter(log -> log.getPerformedBy().equals(adminId))
                .toList();
    }

    //retrive logs for a specific entity type
    public List<AuditLog> getLogsByEntity(String targetEntity) {
        return auditLogRepository.findAll()
                .stream()
                .filter(log -> log.getTargetEntity().equalsIgnoreCase(targetEntity))
                .toList();
    }

    public List<AuditLog> getLogsByAction(String action) {
        return auditLogRepository.findAll()
                .stream()
                .filter(log -> log.getAction().equalsIgnoreCase(action))
                .toList();
    }

    public List<AuditLog> getLogsByDateRange(LocalDateTime from, LocalDateTime to) {
        return auditLogRepository.findAll()
                .stream()
                .filter(log -> !log.getTimestamp().isBefore(from) && !log.getTimestamp().isAfter(to))
                .toList();
    }

    public List<AuditLog> getFilteredLogs(Long adminId, String targetEntity, String action, LocalDateTime from, LocalDateTime to) {
        return auditLogRepository.findAll().stream()
                .filter(log -> (adminId == null || log.getPerformedBy().equals(adminId)))
                .filter(log -> (targetEntity == null || log.getTargetEntity().equalsIgnoreCase(targetEntity)))
                .filter(log -> (action == null || log.getAction().equalsIgnoreCase(action)))
                .filter(log -> (from == null || !log.getTimestamp().isBefore(from)))
                .filter(log -> (to == null || !log.getTimestamp().isAfter(to)))
                .collect(Collectors.toList());
    }
}
