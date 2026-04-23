package com.backend.CreativityMarket.User;

import com.backend.CreativityMarket.Cart.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/assets")
public class AssetController {

    private final AssetRepository assetRepository;
    private final CartService cartService;

    public AssetController(AssetRepository assetRepository, CartService cartService) {
        this.assetRepository = assetRepository;
        this.cartService = cartService;
    }

    @GetMapping("/explore")
    public String explore(@RequestParam(value = "keyword", required = false) String keyword,
                          Model model,
                          HttpSession session) {

        List<Asset> assets = assetRepository.findAll();

        if (keyword != null && !keyword.trim().isEmpty()) {
            String search = keyword.toLowerCase().trim();
            assets = assets.stream()
                    .filter(a -> a.getTitle().toLowerCase().contains(search))
                    .toList();
        }

        model.addAttribute("assets", assets);
        model.addAttribute("keyword", keyword);

        return "user/explore";
    }

    @GetMapping("/{id}")
    public String asset(@PathVariable Long id, Model model, HttpSession session) {
        Asset found = assetRepository.findById(id).orElse(null);

        if (found == null) {
            return "redirect:/";
        }

        Long userId = (Long) session.getAttribute("userId");

        boolean inCart = false;
        if (userId != null) {
            try {
                inCart = cartService.getCartItemsByUserId(userId).stream()
                        .anyMatch(item -> item.getAssetId().equals(id));
            } catch (Exception ignored) {}
        }

        List<Asset> relatedAssets = Collections.emptyList();
        if (found.getCategory() != null) {
            relatedAssets = assetRepository.findByCategory(found.getCategory()).stream()
                    .filter(a -> !a.getId().equals(id))
                    .limit(4)
                    .toList();
        }

        model.addAttribute("asset", found);
        model.addAttribute("inCart", inCart);
        model.addAttribute("inWishlist", false);
        model.addAttribute("relatedAssets", relatedAssets);

        return "user/asset";
    }

    @PostMapping("/{id}/cart")
    public String addToCart(@PathVariable Long id, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            cartService.addItem(userId, id, 1);
        }
        return "redirect:/assets/" + id;
    }

    @PostMapping("/{id}/wishlist")
    public String addToWishlist(@PathVariable Long id) {
        return "redirect:/assets/" + id;
    }

    @PostMapping("/{id}/cart/remove")
    public String removeFromCart(@PathVariable Long id, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            try {
                cartService.getCartItemsByUserId(userId).stream()
                        .filter(item -> item.getAssetId().equals(id))
                        .findFirst()
                        .ifPresent(item -> cartService.removeItem(item.getId()));
            } catch (Exception ignored) {}
        }
        return "redirect:/cart";
    }
}
