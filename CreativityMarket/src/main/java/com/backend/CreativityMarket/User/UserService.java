package com.backend.CreativityMarket.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public long countUsers() {
        return userRepository.count();
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getUsersByRole(String role) {
        return userRepository.findByRole(role);
    }

}
