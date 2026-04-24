package com.backend.CreativityMarket.Admin;

import com.backend.CreativityMarket.User.User;
import com.backend.CreativityMarket.Bounty.BountyService;
import com.backend.CreativityMarket.Moderation.ModerationCaseService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final AdminReportService adminReportService;
    private final BountyService bountyService;
    private final ModerationCaseService moderationCaseService;

    // =========================
    // HELPER (OPTIONAL BUT CLEAN)
    // =========================
    private User getAdmin(HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (user == null || !user.isAdminOrAbove()) {
            throw new SecurityException("Unauthorized");
        }

        return user;
    }

    // =========================
    // DASHBOARD
    // =========================
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {

        User requester = getAdmin(session);

        model.addAttribute("user", requester);
        
        model.addAttribute("userCount", adminReportService.countUsers());
        model.addAttribute("bountyCount", adminReportService.countBounties());
        model.addAttribute("categoryCount", adminReportService.countCategories());
        model.addAttribute("openCases", adminReportService.countOpenCases());

        model.addAttribute("actionsPerDay", adminReportService.getActionsPerDay());
        model.addAttribute("topActions", adminReportService.getTopActions());

        model.addAttribute("caseStatusData", adminReportService.countCasesByStatus());

        model.addAttribute("userRoleData", adminReportService.countUsersByRole());

        model.addAttribute("marketData", adminReportService.countAssetsAndBounties());

        return "admin/dashboard";
    }

    // =========================
    // USERS
    // =========================
    @GetMapping("/users")
    public String users(HttpSession session, Model model) {

        User requester = getAdmin(session);

        model.addAttribute("users", adminService.getUsersForAdmin(requester));
        model.addAttribute("user", requester);

        return "admin/users";
    }

    @PostMapping("/users/{id}/delete")
    public String deleteUser(@PathVariable Long id, HttpSession session) {

        User requester = getAdmin(session);

        adminService.deleteUser(id, requester);

        return "redirect:/admin/users";
    }

    @PostMapping("/users/{id}/promote")
    public String promoteToModerator(@PathVariable Long id, HttpSession session) {

        User requester = getAdmin(session);

        adminService.promoteToModerator(id, requester);

        return "redirect:/admin/users";
    }

    @PostMapping("/users/{id}/ban")
    public String banUser(@PathVariable Long id, HttpSession session) {

        User requester = getAdmin(session);

        adminService.banUser(id, requester);

        return "redirect:/admin/users";
    }

    @PostMapping("/users/{id}/unban")
    public String unbanUser(@PathVariable Long id, HttpSession session) {

        User requester = getAdmin(session);

        adminService.unbanUser(id, requester);

        return "redirect:/admin/users";
    }

    @PostMapping("/users/{id}/suspend")
    public String suspendUser(@PathVariable Long id, HttpSession session) {

        User requester = getAdmin(session);

        adminService.suspendUser(id, requester);

        return "redirect:/admin/users";
    }

    // =========================
    // MODERATION
    // =========================
    @GetMapping("/moderation")
    public String moderationPage(Model model, HttpSession session) {

        User requester = getAdmin(session);

        model.addAttribute("user", requester);
        model.addAttribute("moderators",
                adminService.getUsersByRoleForAdmin(requester, "MODERATOR"));
        model.addAttribute("cases",
                moderationCaseService.getAllCasesForAdmin());

        return "admin/moderation";
    }

    @PostMapping("/moderation/{id}/resolve")
    public String resolveCase(@PathVariable Long id, HttpSession session) {

        User requester = getAdmin(session);

        adminService.resolveCase(id, requester); // ✅ FIXED

        return "redirect:/admin/moderation";
    }

    @PostMapping("/moderation/{id}/dismiss")
    public String dismissCase(@PathVariable Long id, HttpSession session) {

        User requester = getAdmin(session);

        adminService.dismissCase(id, requester); // ✅ FIXED

        return "redirect:/admin/moderation";
    }

    @PostMapping("/moderation/{caseId}/assign/{userId}")
    public String assignCase(@PathVariable Long caseId,
                             @PathVariable Long userId,
                             HttpSession session) {

        User requester = getAdmin(session);

        adminService.assignCase(caseId, userId, requester);

        return "redirect:/admin/moderation";
    }

    // =========================
    // BOUNTIES
    // =========================
    @GetMapping("/bounties")
    public String getBounties(Model model, HttpSession session) {

        User requester = getAdmin(session);

        model.addAttribute("user", requester);
        model.addAttribute("bounties", bountyService.getAllBounties());

        return "admin/bounties";
    }

    @PostMapping("/bounties/{id}/delete")
    public String deleteBounty(@PathVariable Long id, HttpSession session) {

        User requester = getAdmin(session);

        adminService.deleteBounty(id, requester);

        return "redirect:/admin/bounties";
    }
}