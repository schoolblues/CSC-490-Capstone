package com.backend.CreativityMarket.Admin;

import com.backend.CreativityMarket.User.User;
import com.backend.CreativityMarket.User.UserRepository;
import com.backend.CreativityMarket.Category.Category;
import com.backend.CreativityMarket.Category.CategoryRepository;
import com.backend.CreativityMarket.Bounty.Bounty;
import com.backend.CreativityMarket.Bounty.BountyRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminApiController {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final BountyRepository bountyRepository;

    // ---------------- USERS ----------------

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userRepository.deleteById(userId);
    }

    // ---------------- CATEGORIES ----------------

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @PostMapping("/categories")
    public Category createCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @DeleteMapping("/categories/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    // ---------------- BOUNTIES ----------------

    @GetMapping("/bounties")
    public List<Bounty> getAllBounties() {
        return bountyRepository.findAll();
    }

    @DeleteMapping("/bounties/{bountyId}")
    public void deleteBounty(@PathVariable Long bountyId) {
        bountyRepository.deleteById(bountyId);
    }

}
