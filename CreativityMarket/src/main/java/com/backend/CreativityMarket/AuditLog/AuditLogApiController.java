package com.backend.CreativityMarket.AuditLog;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/auditlogs")
@RequiredArgsConstructor
public class AuditLogApiController {

    private final AuditLogService auditLogService;

    // Get all logs
    @GetMapping
    public List<AuditLog> getAllLogs() {
        return auditLogService.getAllLogs();
    }

    // Get logs by admin
    @GetMapping("/admin/{adminId}")
    public List<AuditLog> getLogsByAdmin(@PathVariable Long adminId) {
        return auditLogService.getLogsByAdmin(adminId);
    }

    // Get logs by entity
    @GetMapping("/entity/{entity}")
    public List<AuditLog> getLogsByEntity(@PathVariable String entity) {
        return auditLogService.getLogsByEntity(entity);
    }

    // Get logs by action
    @GetMapping("/action/{action}")
    public List<AuditLog> getLogsByAction(@PathVariable String action) {
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
            @RequestParam(required = false) String entity,
            @RequestParam(required = false) String action,
            @RequestParam(required = false) LocalDateTime from,
            @RequestParam(required = false) LocalDateTime to) {

        return auditLogService.getFilteredLogs(adminId, entity, action, from, to);
    }
}