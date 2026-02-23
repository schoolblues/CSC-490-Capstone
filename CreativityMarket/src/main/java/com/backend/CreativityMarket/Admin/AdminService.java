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
    
    private final UserRepository userRepository;
    private final AuditLogRepository auditLogRepository;

    public List<User> getAllAdmins() {
        return userRepository.findByRole("admin");
    }

    private final List<String> adminPrivileges = List.of(
        "PROMOTE_USER",
        "DELETE_USER",
        "VIEW_ALL_USERS",
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
        return userRepository.findAll();
    }

    //TODO: implement when "artist" role is defined
    /*
    public List<User> getAllArtists() {
        return userRepository.findByRole("artist");
    }
    */

    public void deleteUser(Long userId, Long adminId) {
        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("User with ID " + userId + " not found");
        }
        userRepository.deleteById(userId);
        log("DELETE_USER", adminId, "User", userId);
    }

    //TODO: reconfigure when userService is defined
    public void promoteToAdmin(Long userId, Long adminId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with Id " + userId + " not found"));
        user.setRole("admin");
        userRepository.save(user);
        log("PROMOTE_USER", adminId, "User", userId);
    }

    //TODO: reconfigure when userService is defined
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
