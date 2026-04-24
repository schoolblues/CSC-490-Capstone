package com.backend.CreativityMarket.User;

import com.backend.CreativityMarket.Marketplace.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
                          @RequestParam(value = "fileType", required = false) List<String> fileTypes,
                          @RequestParam(value = "license", required = false) List<String> licenses,
                          @RequestParam(value = "price", required = false) List<String> prices,
                          Model model,
                          HttpSession session) {

        List<Asset> assets = assetRepository.findAll();

        if (keyword != null && !keyword.trim().isEmpty()) {
            String search = keyword.toLowerCase().trim();
            assets = assets.stream()
                    .filter(a -> a.getTitle().toLowerCase().contains(search))
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

        model.addAttribute("assets", assets);
        model.addAttribute("keyword", keyword);
        model.addAttribute("selectedFileTypes", fileTypes != null ? fileTypes : List.of());
        model.addAttribute("selectedLicenses", licenses != null ? licenses : List.of());
        model.addAttribute("selectedPrices", prices != null ? prices : List.of());

        return "user/explore";
    }

    @SuppressWarnings("unchecked")
    @GetMapping("/{id}")
    public String asset(@PathVariable Long id, Model model, HttpSession session) {
        Asset found = assetRepository.findById(id).orElse(null);

        if (found == null) {
            return "redirect:/";
        }

        User user = (User) session.getAttribute("user");

        boolean inCart = false;
        if (user != null) {
            inCart = cartService.getCartItemsByUser(user).stream()
                    .anyMatch(item -> item.getAsset().getId().equals(id));
        }

        List<Asset> relatedAssets = Collections.emptyList();
        if (found.getCategory() != null) {
            relatedAssets = assetRepository.findByCategory(found.getCategory()).stream()
                    .filter(a -> !a.getId().equals(id))
                    .limit(4)
                    .toList();
        }

        List<Asset> wishlist = (List<Asset>) session.getAttribute("wishlist");
        boolean inWishlist = wishlist != null && wishlist.stream().anyMatch(a -> a.getId().equals(id));

        model.addAttribute("asset", found);
        model.addAttribute("inCart", inCart);
        model.addAttribute("inWishlist", inWishlist);
        model.addAttribute("relatedAssets", relatedAssets);

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

    @SuppressWarnings("unchecked")
    @PostMapping("/{id}/wishlist")
    public String addToWishlist(@PathVariable Long id, HttpSession session) {
        Asset asset = assetRepository.findById(id).orElse(null);
        if (asset != null) {
            List<Asset> wishlist = (List<Asset>) session.getAttribute("wishlist");
            if (wishlist == null) wishlist = new ArrayList<>();
            if (wishlist.stream().noneMatch(a -> a.getId().equals(id))) {
                wishlist.add(asset);
            }
            session.setAttribute("wishlist", wishlist);
        }
        return "redirect:/assets/" + id;
    }

    @SuppressWarnings("unchecked")
    @PostMapping("/{id}/wishlist/remove")
    public String removeFromWishlist(@PathVariable Long id, HttpSession session) {
        List<Asset> wishlist = (List<Asset>) session.getAttribute("wishlist");
        if (wishlist != null) {
            wishlist.removeIf(a -> a.getId().equals(id));
            session.setAttribute("wishlist", wishlist);
        }
        return "redirect:/users/home";
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
