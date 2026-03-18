package com.backend.CreativityMarket.AuditLog;

import com.backend.CreativityMarket.Admin.AdminService;
import com.backend.CreativityMarket.User.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/auditlogs")
public class AuditLogController {

    private final AuditLogService auditLogService;
    private final AdminService adminService;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    // View all logs
    @GetMapping
    public String listAllLogs(Model model, @SessionAttribute("adminId") Long adminId) {
        User currentAdmin = adminService.getCurrentAdmin(adminId); // verify admin
        List<AuditLog> logs = auditLogService.getAllLogs();
        model.addAttribute("logs", logs);
        return "admin/auditlogs";
    }

    // Filter logs by admin
    @GetMapping("/admin/{adminIdFilter}")
    public String listLogsByAdmin(@PathVariable Long adminIdFilter,
                                  Model model,
                                  @SessionAttribute("adminId") Long sessionAdminId) {
        User currentAdmin = adminService.getCurrentAdmin(sessionAdminId); // verify admin
        List<AuditLog> logs = auditLogService.getLogsByAdmin(adminIdFilter);
        model.addAttribute("logs", logs);
        return "admin/auditlogs";
    }

    // Filter logs by action
    @GetMapping("/action/{action}")
    public String listLogsByAction(@PathVariable String action,
                                   Model model,
                                   @SessionAttribute("adminId") Long sessionAdminId) {
        User currentAdmin = adminService.getCurrentAdmin(sessionAdminId); // verify admin
        List<AuditLog> logs = auditLogService.getLogsByAction(action);
        model.addAttribute("logs", logs);
        return "admin/auditlogs";
    }

    // Filter logs by target entity
    @GetMapping("/target/{entity}")
    public String listLogsByEntity(@PathVariable String entity,
                                   Model model,
                                   @SessionAttribute("adminId") Long sessionAdminId) {
        User currentAdmin = adminService.getCurrentAdmin(sessionAdminId); // verify admin
        List<AuditLog> logs = auditLogService.getLogsByEntity(entity);
        model.addAttribute("logs", logs);
        return "admin/auditlogs";
    }

    // Filter logs by date range (query parameters: from, to)
    @GetMapping("/date")
    public String listLogsByDate(@RequestParam(required = false) String from,
                                 @RequestParam(required = false) String to,
                                 Model model,
                                 @SessionAttribute("adminId") Long sessionAdminId) {
        User currentAdmin = adminService.getCurrentAdmin(sessionAdminId); // verify admin

        LocalDateTime fromDate = from != null ? LocalDateTime.parse(from, formatter) : null;
        LocalDateTime toDate = to != null ? LocalDateTime.parse(to, formatter) : null;

        List<AuditLog> logs = auditLogService.getLogsByDateRange(fromDate, toDate);
        model.addAttribute("logs", logs);
        return "admin/auditlogs";
    }

    // Flexible filter endpoint (all parameters optional)
    @GetMapping("/filter")
    public String filteredLogs(@RequestParam(required = false) Long adminId,
                               @RequestParam(required = false) String targetEntity,
                               @RequestParam(required = false) String action,
                               @RequestParam(required = false) String from,
                               @RequestParam(required = false) String to,
                               Model model,
                               @SessionAttribute("adminId") Long sessionAdminId) {

        User currentAdmin = adminService.getCurrentAdmin(sessionAdminId); // verify admin

        LocalDateTime fromDate = from != null ? LocalDateTime.parse(from, formatter) : null;
        LocalDateTime toDate = to != null ? LocalDateTime.parse(to, formatter) : null;

        List<AuditLog> logs = auditLogService.getFilteredLogs(adminId, targetEntity, action, fromDate, toDate);
        model.addAttribute("logs", logs);
        return "admin/auditlogs";
    }
}