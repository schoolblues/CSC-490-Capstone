package com.backend.CreativityMarket.AuditLog;

import com.backend.CreativityMarket.Common.EntityType;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/logs")
public class AuditLogApiController {

    private final AuditLogService auditLogService;

    // Get all logs
    @GetMapping
    public List<AuditLog> getAllLogs() {
        return auditLogService.getAllLogs();
    }

    // Get logs by admin
    @GetMapping("/{adminId}")
    public List<AuditLog> getLogsByAdmin(@PathVariable Long adminId) {
        return auditLogService.getLogsByAdmin(adminId);
    }

    // Get logs by entity
    @GetMapping("/entity/{entity}")
    public List<AuditLog> getLogsByEntity(@PathVariable EntityType entity) {
        return auditLogService.getLogsByEntity(entity);
    }

    // Get logs by action
    @GetMapping("/action/{action}")
    public List<AuditLog> getLogsByAction(@PathVariable AuditAction action) {
        return auditLogService.getLogsByAction(action);
    }

    // Get logs by date range
    @GetMapping("/daterange")
    public List<AuditLog> getLogsByDateRange(
            @RequestParam LocalDateTime from,
            @RequestParam LocalDateTime to) {

        return auditLogService.getLogsByDateRange(from, to);
    }

    // Fully filtered logs
    @GetMapping("/filter")
    public List<AuditLog> filterLogs(
            @RequestParam(required = false) Long adminId,
            @RequestParam(required = false) EntityType entity,
            @RequestParam(required = false) AuditAction action,
            @RequestParam(required = false) LocalDateTime from,
            @RequestParam(required = false) LocalDateTime to) {

        return auditLogService.getFilteredLogs(adminId, action, entity, from, to);
    }
}