package com.backend.CreativityMarket.User;

import com.backend.CreativityMarket.Marketplace.CartService;
import com.backend.CreativityMarket.Review.Review;
import com.backend.CreativityMarket.Review.ReviewRepository;
import com.backend.CreativityMarket.Marketplace.OrderRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/assets")
public class AssetController {

    private final AssetRepository assetRepository;
    private final CartService cartService;
    private final WishlistItemRepository wishlistItemRepository;
    private final ReviewRepository reviewRepository;
    private final OrderRepository orderRepository;

    public AssetController(AssetRepository assetRepository,
                           CartService cartService,
                           WishlistItemRepository wishlistItemRepository,
                           ReviewRepository reviewRepository,
                           OrderRepository orderRepository) {
        this.assetRepository = assetRepository;
        this.cartService = cartService;
        this.wishlistItemRepository = wishlistItemRepository;
        this.reviewRepository = reviewRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/explore")
    public String explore(@RequestParam(value = "keyword", required = false) String keyword,
                          @RequestParam(value = "fileType", required = false) List<String> fileTypes,
                          @RequestParam(value = "license", required = false) List<String> licenses,
                          @RequestParam(value = "price", required = false) List<String> prices,
                          Model model,
                          HttpSession session) {

        List<Asset> assets = assetRepository.findAll();

        if (keyword != null && !keyword.trim().isEmpty()) {
            String search = keyword.toLowerCase().trim();
            assets = assets.stream()
                    .filter(a -> a.getTitle() != null && a.getTitle().toLowerCase().contains(search))
                    .toList();
        }

        if (fileTypes != null && !fileTypes.isEmpty()) {
            List<String> normalised = fileTypes.stream().map(String::toUpperCase).toList();
            assets = assets.stream()
                    .filter(a -> a.getFileType() != null && normalised.contains(a.getFileType().toUpperCase()))
                    .toList();
        }

        if (licenses != null && !licenses.isEmpty()) {
            List<String> normalised = licenses.stream().map(String::toLowerCase).toList();
            assets = assets.stream()
                    .filter(a -> a.getLicense() != null && normalised.contains(a.getLicense().toLowerCase()))
                    .toList();
        }

        if (prices != null && !prices.isEmpty()) {
            boolean wantFree = prices.contains("free");
            boolean wantPaid = prices.contains("paid");
            assets = assets.stream()
                    .filter(a -> (wantFree && a.getPrice() == 0) || (wantPaid && a.getPrice() > 0))
                    .toList();
        }

        User user = (User) session.getAttribute("user");
        Set<Long> wishlistedIds = Collections.emptySet();
        if (user != null) {
            wishlistedIds = wishlistItemRepository.findByUser(user).stream()
                    .map(w -> w.getAsset().getId())
                    .collect(Collectors.toSet());
        }

        model.addAttribute("assets", assets);
        model.addAttribute("keyword", keyword);
        model.addAttribute("selectedFileTypes", fileTypes != null ? fileTypes : List.of());
        model.addAttribute("selectedLicenses", licenses != null ? licenses : List.of());
        model.addAttribute("selectedPrices", prices != null ? prices : List.of());
        model.addAttribute("wishlistedIds", wishlistedIds);

        return "user/explore";
    }

    @GetMapping("/{id}")
    public String asset(@PathVariable Long id,
                        @RequestParam(value = "reviewError", required = false) String reviewError,
                        Model model,
                        HttpSession session) {
        Asset found = assetRepository.findById(id).orElse(null);

        if (found == null) {
            return "redirect:/";
        }

        User user = (User) session.getAttribute("user");

        boolean inCart = false;
        boolean inWishlist = false;
        boolean hasPurchased = false;
        boolean hasReviewed = false;

        if (user != null) {
            inCart = cartService.getCartItemsByUser(user).stream()
                    .anyMatch(item -> item.getAsset().getId().equals(id));
            inWishlist = wishlistItemRepository.existsByUserAndAsset(user, found);

            hasPurchased = orderRepository.findByUserIdWithItemsOrderByCreatedAtDesc(user.getId())
                    .stream()
                    .flatMap(o -> o.getItems().stream())
                    .anyMatch(oi -> oi.getAsset() != null && id.equals(oi.getAsset().getId()));

            hasReviewed = reviewRepository.existsByAssetAndUser(found, user);
        }

        List<Asset> relatedAssets = Collections.emptyList();
        if (found.getCategory() != null) {
            relatedAssets = assetRepository.findByCategory(found.getCategory()).stream()
                    .filter(a -> !a.getId().equals(id))
                    .limit(4)
                    .toList();
        }

        List<Review> reviews = reviewRepository.findByAssetOrderByCreatedAtDesc(found);

        model.addAttribute("asset", found);
        model.addAttribute("inCart", inCart);
        model.addAttribute("inWishlist", inWishlist);
        model.addAttribute("relatedAssets", relatedAssets);
        model.addAttribute("reviews", reviews);
        model.addAttribute("hasPurchased", hasPurchased);
        model.addAttribute("hasReviewed", hasReviewed);
        model.addAttribute("reviewError", reviewError);

        return "user/detailedItemView";
    }

    @PostMapping("/{id}/cart")
    public String addToCart(@PathVariable Long id, HttpSession session,
                            @RequestHeader(value = "Referer", required = false) String referer) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/signin";
        }
        cartService.addItem(user, id, 1);
        return referer != null ? "redirect:" + referer : "redirect:/assets/" + id;
    }

    @PostMapping("/{id}/wishlist")
    public String addToWishlist(@PathVariable Long id,
                                HttpSession session,
                                @RequestHeader(value = "Referer", required = false) String referer) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/signin";

        Asset asset = assetRepository.findById(id).orElse(null);
        if (asset != null && !wishlistItemRepository.existsByUserAndAsset(user, asset)) {
            wishlistItemRepository.save(new WishlistItem(user, asset));
        }
        return referer != null ? "redirect:" + referer : "redirect:/assets/" + id;
    }

    @Transactional
    @PostMapping("/{id}/wishlist/remove")
    public String removeFromWishlist(@PathVariable Long id,
                                     HttpSession session,
                                     @RequestHeader(value = "Referer", required = false) String referer) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/signin";

        Asset asset = assetRepository.findById(id).orElse(null);
        if (asset != null) {
            wishlistItemRepository.deleteByUserAndAsset(user, asset);
        }
        return referer != null ? "redirect:" + referer : "redirect:/users/home";
    }

    @GetMapping("/{id}/download")
    public String downloadAsset(@PathVariable Long id) {
        return "redirect:/assets/" + id;
    }

    @PostMapping("/{id}/cart/remove")
    public String removeFromCart(@PathVariable Long id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            cartService.getCartItemsByUser(user).stream()
                    .filter(item -> item.getAsset().getId().equals(id))
                    .findFirst()
                    .ifPresent(item -> cartService.removeItem(item.getId()));
        }
        return "redirect:/cart";
    }
}
