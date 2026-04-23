package com.backend.CreativityMarket.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailIgnoreCase(String email);
    boolean existsByEmail(String email);
    List<User> findByRole(String role);
    //List<User> findByRole(Role role);
    List<User> findByRoleNotIn(List<String> roles);
    List<User> findByRoleNotInAndBannedFalse(List<String> roles);

    @Query("""
        SELECT u.role, COUNT(u)
        FROM User u
        GROUP BY u.role
    """)
    List<Object[]> countUsersByRole();
}
