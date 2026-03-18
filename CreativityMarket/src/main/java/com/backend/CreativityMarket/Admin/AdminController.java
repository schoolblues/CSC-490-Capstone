package com.backend.CreativityMarket.Admin;

import com.backend.CreativityMarket.User.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    // Render admin dashboard template
    @GetMapping("/dashboard")
    public String dashboard(Model model, @SessionAttribute("adminId") Long adminId) {
        User currentAdmin = adminService.getCurrentAdmin(adminId);
        model.addAttribute("admin", currentAdmin);
        model.addAttribute("privileges", adminService.getAdminPrivileges());
        return "admin/dashboard"; // Freemarker template: /templates/admin/dashboard.ftl
    }

    // List all users
    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> users = adminService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/users"; // template: /templates/admin/users.ftl
    }

    // List all moderators
    @GetMapping("/moderators")
    public String listModerators(Model model) {
        List<User> moderators = adminService.getAllModerators();
        model.addAttribute("moderators", moderators);
        return "admin/moderators"; // template: /templates/admin/moderators.ftl
    }

    // Promote a user to moderator (POST action)
    @PostMapping("/promote/moderator/{userId}")
    public String promoteToModerator(@PathVariable Long userId, @SessionAttribute("adminId") Long adminId) {
        adminService.promoteToModerator(userId, adminId);
        return "redirect:/admin/users"; // refresh the users page
    }

    // Delete a user
    @PostMapping("/delete/user/{userId}")
    public String deleteUser(@PathVariable Long userId, @SessionAttribute("adminId") Long adminId) {
        adminService.deleteUser(userId, adminId);
        return "redirect:/admin/users";
    }
}