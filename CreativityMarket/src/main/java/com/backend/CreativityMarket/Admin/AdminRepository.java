package com.backend.CreativityMarket.Admin;

import com.backend.CreativityMarket.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<User, Long> {

    List<User> findAllByRole(String role);  // use role="admin"

    Optional<User> findByIdAndRole(Long id, String role);  // use role="admin"
}