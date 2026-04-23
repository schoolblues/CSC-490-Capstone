package com.backend.CreativityMarket.Admin;

import com.backend.CreativityMarket.Marketplace.Category;
import com.backend.CreativityMarket.User.Asset;
import com.backend.CreativityMarket.User.AssetRepository;
import com.backend.CreativityMarket.User.User;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminApiController {

    private final AdminService adminService;
    private final AssetRepository assetRepository;

    // =========================
    // USERS
    // =========================

    @GetMapping("/users")
    public List<User> getUsers(HttpSession session) {
        User requester = (User) session.getAttribute("user");
        return adminService.getUsersForAdmin(requester);
    }

    @GetMapping("/users/role/{role}")
    public List<User> getUsersByRole(@PathVariable String role,
                                      HttpSession session) {
        User requester = (User) session.getAttribute("user");
        return adminService.getUsersByRoleForAdmin(requester, role);
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable Long userId,
                           HttpSession session) {
        User requester = (User) session.getAttribute("user");
        adminService.deleteUser(userId, requester);
    }

    @PostMapping("/users/{userId}/promote/moderator")
    public void promoteToModerator(@PathVariable Long userId,
                                    HttpSession session) {
        User requester = (User) session.getAttribute("user");
        adminService.promoteToModerator(userId, requester);
    }

    // =========================
    // BOUNTIES
    // =========================

    @DeleteMapping("/bounties/{bountyId}")
    public void deleteBounty(@PathVariable Long bountyId,
                             HttpSession session) {
        User requester = (User) session.getAttribute("user");
        adminService.deleteBounty(bountyId, requester);
    }

    // =========================
    // CATEGORIES
    // =========================
    // CREATE (JSON)
    @PostMapping("/categories/new")
    public Category create(@RequestBody Category category, HttpSession session) {

        User requester = (User) session.getAttribute("user");
        adminService.createCategory(category.getName(), category.getDescription(), requester);
        return category;
    }

    // UPDATE (JSON)
    @PutMapping("/categories/{id}")
    public Category update(@PathVariable Long id,
                           @RequestBody Category category, HttpSession session) {
        User requester = (User) session.getAttribute("user");
        adminService.updateCategory(id, category.getName(), category.getDescription(), requester);
        return category;
    }

    // DELETE
    @DeleteMapping("/categories/{id}")
    public void delete(@PathVariable Long id, HttpSession session) {
        User requester = (User) session.getAttribute("user");
        adminService.deleteCategory(id, requester);
    }

    // ==========
    // ASSETS
    // ==========
    @GetMapping("/assets/{id}")
    public Asset getAsset(@PathVariable Long id) {

        return assetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asset not found: " + id));
    }
}