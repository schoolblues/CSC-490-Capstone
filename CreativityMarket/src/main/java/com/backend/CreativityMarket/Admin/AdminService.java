package com.backend.CreativityMarket.Admin;

import com.backend.CreativityMarket.Bounty.BountyRepository;
import com.backend.CreativityMarket.Moderation.ModerationCase;
import com.backend.CreativityMarket.Moderation.ModerationCaseRepository;
import com.backend.CreativityMarket.Moderation.ModerationCaseService;
import com.backend.CreativityMarket.User.User;
import com.backend.CreativityMarket.User.UserRepository;
import com.backend.CreativityMarket.User.UserService;
import com.backend.CreativityMarket.AuditLog.AuditAction;
import com.backend.CreativityMarket.AuditLog.AuditLogService;
import com.backend.CreativityMarket.Bounty.Bounty;
import com.backend.CreativityMarket.Bounty.BountyService;
import com.backend.CreativityMarket.Marketplace.CategoryService;
import com.backend.CreativityMarket.User.Asset;
import com.backend.CreativityMarket.User.AssetRepository;
import com.backend.CreativityMarket.Common.EntityType;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminService {

    private final ModerationCaseRepository moderationCaseRepository;
    private final ModerationCaseService moderationCaseService;
    private final UserRepository userRepository;
    private final AuditLogService auditLogService;
    private final UserService userService;
    private final BountyRepository bountyRepository;
    private final BountyService bountyService;
    private final CategoryService categoryService;
    private final AssetRepository assetRepository;

    // PRIVILEGES (MVP list - fine as-is)
    private final List<String> adminPrivileges = List.of(
            "PROMOTE_USER_TO_MODERATOR",
            "DELETE_USER",
            "VIEW_ALL_USERS",
            "VIEW_ALL_ARTISTS",
            "VIEW_ALL_MODERATORS",
            "APPROVE_CONTENT",
            "MANAGE_USER_ACCOUNT",
            "MONITOR_TRANSACTION",
            "GENERATE_REPORTS",
            "DELETE_BOUNTY",
            "MANAGE_CATEGORIES"
    );

    public List<String> getAdminPrivileges() {
        return adminPrivileges;
    }

    // =========================
    // SECURITY GATE (SINGLE RULE)
    // =========================
    private void validateAdminOrAbove(User requester) {
        if (requester == null || !requester.isAdminOrAbove()) {
            throw new SecurityException("Unauthorized admin action");
        }
    }

    // =========================
    // USERS
    // =========================

    public List<User> getUsersForAdmin(User requester) {
        validateAdminOrAbove(requester);

        // SIMPLE RULE:
        // admin + superadmin both see all non-admin users
        return userRepository.findByRoleNotIn(List.of("ADMIN", "SUPERADMIN"));
    }

    public List<User> getActiveUsersForAdmin(User requester) {
        validateAdminOrAbove(requester);

        return userRepository.findByRoleNotInAndBannedFalse(List.of("ADMIN", "SUPERADMIN"));
    }

    public List<User> getUsersByRoleForAdmin(User requester, String role) {
        validateAdminOrAbove(requester);

        return userService.getUsersByRole(role)
                .stream()
                .filter(u -> !u.isSuperAdmin())
                .toList();
    }

    public void deleteUser(Long userId, User requester) {
        validateAdminOrAbove(requester);

        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("User not found: " + userId);
        }

        userRepository.deleteById(userId);
        auditLogService.logAction(requester.getId(),
                                    AuditAction.DELETE_USER,
                                    EntityType.USER,
                                    userId);
    }

    public void promoteToModerator(Long userId, User requester) {
        validateAdminOrAbove(requester);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + userId));

        user.setRole("MODERATOR");
        userRepository.save(user);
        auditLogService.logAction(requester.getId(),
                                    AuditAction.PROMOTE_USER_TO_MODERATOR,
                                    EntityType.USER,
                                    userId);
    }

    public void banUser(Long userId, User requester) {
        validateAdminOrAbove(requester);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + userId));
        
        user.setBanned(true);
        userRepository.save(user);
        auditLogService.logAction(requester.getId(),
                                    AuditAction.BAN_USER,
                                    EntityType.USER,
                                    userId);
    }

    public void unbanUser(Long userId, User requester) {
        validateAdminOrAbove(requester);

        User user = userRepository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException("User not found: " + userId));

        user.setBanned(false);
        userRepository.save(user);
        
        auditLogService.logAction(requester.getId(),
                                    AuditAction.UNBAN_USER,
                                    EntityType.USER,
                                    userId);
    }

    public void suspendUser(Long userId, User requester) {
        validateAdminOrAbove(requester);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + userId));

        user.setSuspendedUntil(LocalDateTime.now().plusDays(30));
        userRepository.save(user);
        auditLogService.logAction(requester.getId(),
                                    AuditAction.SUSPEND_USER,
                                    EntityType.USER,
                                    userId);
    }

    public void unsuspendUser(Long userId, User requester) {
        validateAdminOrAbove(requester);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + userId));

        user.setSuspendedUntil(null);
        userRepository.save(user);
        auditLogService.logAction(requester.getId(),
                                    AuditAction.UNSUSPEND_USER,
                                    EntityType.USER,
                                    userId);
    }

    public void updateUser(User user, User requester) {
        validateAdminOrAbove(requester);

        userRepository.save(user);

        auditLogService.logAction(requester.getId(),
                                    AuditAction.UPDATE_USER,
                                    EntityType.USER,
                                    user.getId());
    }

    // =========================
    // BOUNTIES
    // =========================

    public void createBounty(Bounty bounty, User requester) {
        validateAdminOrAbove(requester);

        bountyService.createBounty(bounty, requester);

        auditLogService.logAction(requester.getId(),
                                    AuditAction.CREATE_BOUNTY,
                                    EntityType.BOUNTY,
                                    null);
    }

    public void updateBounty(Bounty bounty, User requester) {
        validateAdminOrAbove(requester);

        bountyRepository.save(bounty);

        auditLogService.logAction(requester.getId(),
                                    AuditAction.UPDATE_BOUNTY,
                                    EntityType.BOUNTY,
                                    bounty.getId());
    }

    public void deleteBounty(Long bountyId, User requester) {
        validateAdminOrAbove(requester);

        bountyService.deleteBounty(bountyId);

        auditLogService.logAction(requester.getId(),
                                    AuditAction.DELETE_BOUNTY,
                                    EntityType.BOUNTY,
                                    bountyId);
    }

    // =========================
    // CATEGORIES
    // =========================
    public void createCategory(String name, String description, User requester) {
        validateAdminOrAbove(requester);

        categoryService.createCategory(name, description);

        auditLogService.logAction(requester.getId(),
                                    AuditAction.CREATE_CATEGORY,
                                    EntityType.CATEGORY,
                                    null);
    }
    
    public void updateCategory(Long categoryId, String name, String description, User requester) {
        validateAdminOrAbove(requester);

        categoryService.updateCategory(categoryId, name, description);

        auditLogService.logAction(requester.getId(),
                                    AuditAction.UPDATE_CATEGORY,
                                    EntityType.CATEGORY,
                                    categoryId);
    }

    public void deleteCategory(Long categoryId, User requester) {
        validateAdminOrAbove(requester);

        categoryService.deleteCategory(categoryId);

        auditLogService.logAction(requester.getId(),
                                    AuditAction.DELETE_CATEGORY,
                                    EntityType.CATEGORY,
                                    categoryId);
    }

    // =========================
    // ASSETS
    // =========================
    public void createAsset(Asset asset, User requester) {
        validateAdminOrAbove(requester);

        assetRepository.save(asset);

        auditLogService.logAction(requester.getId(),
                                    AuditAction.CREATE_ASSET,
                                    EntityType.ASSET,
                                    asset.getId());
    }

    public void updateAsset(Asset asset, User requester) {
        validateAdminOrAbove(requester);

        assetRepository.save(asset);

        auditLogService.logAction(requester.getId(),
                                    AuditAction.UPDATE_ASSET,
                                    EntityType.ASSET,
                                    asset.getId());
    }

    public void deleteAsset(Long assetId, User requester) {
        validateAdminOrAbove(requester);

        assetRepository.deleteById(assetId);

        auditLogService.logAction(requester.getId(),
                                    AuditAction.DELETE_ASSET,
                                    EntityType.ASSET,
                                    assetId);
    }

    // =========================
    // MODERATION
    // =========================
    public void createCase(ModerationCase mc, User requester) {
        validateAdminOrAbove(requester);

        moderationCaseRepository.save(mc);

        auditLogService.logAction(requester.getId(),
                                    AuditAction.CREATE_CASE,
                                    EntityType.MODERATION_CASE,
                                    mc.getId());
    }

    public void assignCase(Long mcId, Long userId, User requester) {
        validateAdminOrAbove(requester);

        User user = userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("user not found : " + userId));

        ModerationCase mc = moderationCaseRepository.findById(mcId)
                    .orElseThrow(() -> new EntityNotFoundException("case not fount: " + mcId));

        mc.setHandledBy(user);
        moderationCaseRepository.save(mc);            
        
        auditLogService.logAction(requester.getId(),
                                    AuditAction.ASSIGN_CASE,
                                    EntityType.MODERATION_CASE,
                                    mcId);
        
    }

    public void updateCase(ModerationCase mc, User requester) {
        validateAdminOrAbove(requester);

        moderationCaseRepository.save(mc);

        auditLogService.logAction(requester.getId(),
                                    AuditAction.UPDATE_CASE,
                                    EntityType.MODERATION_CASE,
                                    mc.getId());
    }
    
    public void resolveCase(Long caseId, User requester) {
        validateAdminOrAbove(requester);

        moderationCaseService.resolveCase(caseId, requester);
        auditLogService.logAction(requester.getId(),
                                    AuditAction.RESOLVE_CASE,
                                    EntityType.MODERATION_CASE,
                                    caseId);
    }

    public void dismissCase(Long caseId, User requester) {
        validateAdminOrAbove(requester);

        moderationCaseService.dismissCase(caseId, requester);
        auditLogService.logAction(requester.getId(),
                                    AuditAction.DISMISS_CASE,
                                    EntityType.MODERATION_CASE,
                                    caseId);
    }
    
}