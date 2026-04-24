package com.backend.CreativityMarket.Review;

import com.backend.CreativityMarket.Marketplace.OrderRepository;
import com.backend.CreativityMarket.User.Asset;
import com.backend.CreativityMarket.User.AssetRepository;
import com.backend.CreativityMarket.User.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final AssetRepository assetRepository;
    private final OrderRepository orderRepository;

    public ReviewController(ReviewRepository reviewRepository,
                            AssetRepository assetRepository,
                            OrderRepository orderRepository) {
        this.reviewRepository = reviewRepository;
        this.assetRepository = assetRepository;
        this.orderRepository = orderRepository;
    }

    @PostMapping("/asset/{assetId}")
    public String createReview(@PathVariable Long assetId,
                               @RequestParam int rating,
                               @RequestParam String body,
                               HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/signin";

        Asset asset = assetRepository.findById(assetId).orElse(null);
        if (asset == null) return "redirect:/";

        boolean hasPurchased = orderRepository.findByUserIdWithItemsOrderByCreatedAtDesc(user.getId())
                .stream()
                .flatMap(o -> o.getItems().stream())
                .anyMatch(oi -> oi.getAsset() != null && assetId.equals(oi.getAsset().getId()));

        if (!hasPurchased) {
            return "redirect:/assets/" + assetId + "?reviewError=purchase";
        }

        if (reviewRepository.existsByAssetAndUser(asset, user)) {
            return "redirect:/assets/" + assetId + "?reviewError=duplicate";
        }

        if (rating < 1) rating = 1;
        if (rating > 5) rating = 5;

        Review r = new Review();
        r.setAsset(asset);
        r.setUser(user);
        r.setRating(rating);
        r.setBody(body);
        reviewRepository.save(r);

        return "redirect:/assets/" + assetId + "#reviews";
    }

    @PostMapping("/{reviewId}/flag")
    public String flagReview(@PathVariable Long reviewId,
                             @RequestHeader(value = "Referer", required = false) String referer,
                             HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/signin";

        reviewRepository.findById(reviewId).ifPresent(r -> {
            r.setFlagged(true);
            reviewRepository.save(r);
        });

        return referer != null ? "redirect:" + referer : "redirect:/";
    }
}
