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
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

@GetMapping
public String listLogs(
        @RequestParam(required = false) Long adminId,
        @RequestParam(required = false) AuditAction action,
        @RequestParam(required = false) EntityType entity,
        @RequestParam(required = false) String from,
        @RequestParam(required = false) String to,
        Model model) {

    LocalDateTime fromDate = (from != null && !from.isEmpty())
            ? LocalDateTime.parse(from, formatter)
            : null;

    LocalDateTime toDate = (to != null && !to.isEmpty())
            ? LocalDateTime.parse(to, formatter)
            : null;

    List<AuditLog> logs = auditLogService.getFilteredLogs(
            adminId,
            action,
            entity,
            fromDate,
            toDate
    );

    model.addAttribute("logs", logs);

    return "admin/logs";
}
}