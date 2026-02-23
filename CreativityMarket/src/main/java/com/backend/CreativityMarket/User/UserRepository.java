package com.backend.CreativityMarket.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find users by email (exact match)
    Optional<User> findByEmail(String email);

    // Check if a user exists by email
    boolean existsByEmail(String email);

    // Find all users whose email contains the search string, case-insensitive
    List<User> findByEmailContainingIgnoreCase(String emailFragment);

    // Find all users by role
    List<User> findByRole(String role);
}