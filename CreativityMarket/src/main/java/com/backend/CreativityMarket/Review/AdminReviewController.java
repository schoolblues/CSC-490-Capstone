package com.backend.CreativityMarket.Review;

import com.backend.CreativityMarket.User.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/reviews")
public class AdminReviewController {

    private final ReviewRepository reviewRepository;

    public AdminReviewController(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    private User getAdmin(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null || !user.isAdminOrAbove()) {
            throw new SecurityException("Unauthorized");
        }
        return user;
    }

    @GetMapping
    public String reviewsPage(Model model, HttpSession session) {
        User admin = getAdmin(session);
        model.addAttribute("user", admin);
        model.addAttribute("reviews", reviewRepository.findAllByOrderByCreatedAtDesc());
        model.addAttribute("flaggedReviews", reviewRepository.findByFlaggedTrueOrderByCreatedAtDesc());
        return "admin/reviews";
    }

    @PostMapping("/{id}/delete")
    public String deleteReview(@PathVariable Long id, HttpSession session) {
        getAdmin(session);
        reviewRepository.deleteById(id);
        return "redirect:/admin/reviews";
    }

    @PostMapping("/{id}/clear-flag")
    public String clearFlag(@PathVariable Long id, HttpSession session) {
        getAdmin(session);
        reviewRepository.findById(id).ifPresent(r -> {
            r.setFlagged(false);
            reviewRepository.save(r);
        });
        return "redirect:/admin/reviews";
    }
}
