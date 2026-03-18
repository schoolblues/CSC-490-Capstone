package com.backend.CreativityMarket.Admin;

import com.backend.CreativityMarket.User.User;
import com.backend.CreativityMarket.User.UserRepository;
import com.backend.CreativityMarket.AuditLog.AuditLog;
import com.backend.CreativityMarket.AuditLog.AuditLogRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminService {
    
    private final AdminRepository adminRepository;
    private final AuditLogRepository auditLogRepository;
    private final UserRepository userRepository;

    public List<User> getAllAdmins() {
        return adminRepository.findAllByRole("admin");
    }

    public User getCurrentAdmin(Long sessionAdminId) {
    return adminRepository.findByIdAndRole(sessionAdminId, "admin")
            .orElseThrow(() -> new EntityNotFoundException("Admin not found"));
}

    private final List<String> adminPrivileges = List.of(
        "PROMOTE_USER_TO_MODERATOR",
        "DELETE_USER",
        "VIEW_ALL_USERS",
        "vIEW_ALL_ARTISTS",
        "VIEW_ALL_MODERATORS",
        "APPROVE_CONTENT",
        "MANAGE_USER_ACCOUNT",
        "MONITOR_TRANSACTION",
        "GENERATE_REPORTS"
    );

    //helper method to log admin actions
    private void log(String action, Long adminId, String targetEntity, Long targetId) {
        auditLogRepository.save(new AuditLog(action, adminId, targetEntity, targetId));
    }

    public List<String> getAdminPrivileges() {
        return adminPrivileges;
    }

    //User management actions
    public List<User> getAllUsers() {
        return userRepository.findAllByRole("user");
    }

    public List<User> getAllArtists() {
        return userRepository.findAllByRole("artist");
    }
    
    public List<User> getAllModerators() {
        return userRepository.findAllByRole("mod");
    }

    public void deleteUser(Long userId, Long adminId) {
        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("User with ID " + userId + " not found");
        }
        userRepository.deleteById(userId);
        log("DELETE_USER", adminId, "User", userId);
    }

    public void promoteToModerator(Long userId, Long adminId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with Id " + userId + " not found"));
        user.setRole("mod");
        userRepository.save(user);
        log("PROMOTE_USER_TO_MODERATOR", adminId, "User", userId);
    }

    public boolean userExists(String email) {
        return userRepository.existsByEmail(email);
    }

    //Admin functional actions
    public void approveContent(Long contentId, Long adminId) {
        //TODO: implement content approval logic
        System.out.println("content approved: " + contentId);
        log("APPROVE_CONTENT", adminId, "Content", contentId);
    }

    public void manageUserAccount(Long userId, Long adminId) {
        //TODO: implement user account management logic
        System.out.println("Managing account for user: " + userId);
        log("MANAGE_USER_ACCOUNT", adminId, "User", userId);
    }

    public void monitorTransaction(Long transactionId, Long adminId) {
        //TODO: implement transaction monitoring logic
        System.out.println("Monitoring transaction: " + transactionId);
        log("MONITOR_TRANSACTION", adminId, "Transaction", transactionId);
    }

    public void generateReports(Long adminId) {
        //TODO: implement report generation logic
        System.out.println("Generating admin reports...");
        log("GENERATE_REPORTS", adminId, "Report", null);
    }
}
