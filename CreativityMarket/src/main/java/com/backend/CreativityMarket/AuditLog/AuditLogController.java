package com.backend.CreativityMarket.AuditLog;

import com.backend.CreativityMarket.Common.EntityType;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/logs")
public class AuditLogController {

    private final AuditLogService auditLogService;

    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    @GetMapping
    public String listAllLogs(Model model) {
        List<AuditLog> logs = auditLogService.getAllLogs();
        model.addAttribute("logs", logs);
        return "admin/logs";
    }

    @GetMapping("/admin/{adminIdFilter}")
    public String listLogsByAdmin(@PathVariable Long adminIdFilter, Model model) {
        List<AuditLog> logs = auditLogService.getLogsByAdmin(adminIdFilter);
        model.addAttribute("logs", logs);
        return "admin/logs";
    }

    @GetMapping("/action/{action}")
    public String listLogsByAction(@PathVariable String action, Model model) {
        List<AuditLog> logs = auditLogService.getLogsByAction(AuditAction.valueOf(action.toUpperCase()));
        model.addAttribute("logs", logs);
        return "admin/logs";
    }

    @GetMapping("/target/{entity}")
    public String listLogsByEntity(@PathVariable String entity, Model model) {
        List<AuditLog> logs = auditLogService.getLogsByEntity(EntityType.valueOf(entity.toUpperCase()));
        model.addAttribute("logs", logs);
        return "admin/logs";
    }

    @GetMapping("/date")
    public String listLogsByDate(@RequestParam(required = false) String from,
                                 @RequestParam(required = false) String to,
                                 Model model) {
        LocalDateTime fromDate = from != null ? LocalDateTime.parse(from, formatter) : null;
        LocalDateTime toDate = to != null ? LocalDateTime.parse(to, formatter) : null;
        List<AuditLog> logs = auditLogService.getLogsByDateRange(fromDate, toDate);
        model.addAttribute("logs", logs);
        return "admin/logs";
    }

    @GetMapping("/filter")
    public String filteredLogs(@RequestParam(required = false) Long adminId,
                               @RequestParam(required = false) String targetEntity,
                               @RequestParam(required = false) String action,
                               @RequestParam(required = false) String from,
                               @RequestParam(required = false) String to,
                               Model model) {
        LocalDateTime fromDate = from != null ? LocalDateTime.parse(from, formatter) : null;
        LocalDateTime toDate = to != null ? LocalDateTime.parse(to, formatter) : null;

        AuditAction auditAction = action != null ? AuditAction.valueOf(action.toUpperCase()) : null;
        EntityType entityType = targetEntity != null ? EntityType.valueOf(targetEntity.toUpperCase()) : null;

        List<AuditLog> logs = auditLogService.getFilteredLogs(adminId, auditAction, entityType, fromDate, toDate);
        model.addAttribute("logs", logs);
        return "admin/logs";
    }
}
