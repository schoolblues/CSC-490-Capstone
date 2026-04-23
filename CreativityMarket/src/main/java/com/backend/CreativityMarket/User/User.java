package com.backend.CreativityMarket.User;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "users") 
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    private String role;

    private String createdAt;

    private boolean banned = false;
    private LocalDateTime suspendedUntil;

    public User() {}

    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public boolean isBanned() {return banned; }
    public void setBanned(boolean banned) {this.banned = banned; }

    public LocalDateTime getSuspendedUntil() {return suspendedUntil; }
    public void setSuspendedUntil(LocalDateTime suspendedUntil) {this.suspendedUntil = suspendedUntil; }

    public boolean isModerator() {
        return "MODERATOR".equalsIgnoreCase(this.role);
    }
    public boolean isAdmin() {
        return "ADMIN".equalsIgnoreCase(this.role);
    }

    public boolean isSuperAdmin() {
        return "SUPERADMIN".equalsIgnoreCase(this.role);
    }

    public boolean isAdminOrAbove() {
        return isAdmin() || isSuperAdmin();
    }

    public boolean isSuspended() {
        return suspendedUntil != null && suspendedUntil.isAfter(LocalDateTime.now());
    }
}
