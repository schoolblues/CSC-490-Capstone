package com.backend.CreativityMarket.User;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/assets")
public class AssetController {

    private final AssetRepository assetRepository;

    public AssetController(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @GetMapping("/explore")
    public String explore(@RequestParam(value = "keyword", required = false) String keyword,
                      Model model,
                      HttpSession session) {

    List<Asset> assets = sampleAssets();

    if (keyword != null && !keyword.trim().isEmpty()) {
        String search = keyword.toLowerCase().trim();

        assets = assets.stream()
                .filter(a ->
                        (a.getTitle() != null && a.getTitle().toLowerCase().contains(search)) ||
                        (a.getFileType() != null && a.getFileType().toLowerCase().contains(search))
                )
                .toList();
    }

    model.addAttribute("assets", assets);
    model.addAttribute("keyword", keyword);
    model.addAttribute("cartCount", getCart(session).size());
    model.addAttribute("wishlistCount", getWishlist(session).size());

    return "user/explore";
    }

    @GetMapping("/{id}")
    public String asset(@PathVariable Long id, Model model) {
        Asset found = assetRepository.findById(id).orElse(null);

        if (found == null) {
            return "redirect:/";
        }

        model.addAttribute("asset", found);
        return "user/asset";
    }

    @PostMapping("/{id}/cart")
    public String addToCart(@PathVariable Long id, HttpSession session) {
        Asset found = findAssetById(id);
        List<Asset> cart = getCart(session);

        if (found != null && cart.stream().noneMatch(a -> a.getId().equals(id))) {
            cart.add(found);
        }

        return "redirect:/assets/" + id;
    }

    @PostMapping("/{id}/cart/remove")
    public String removeFromCart(@PathVariable Long id, HttpSession session) {
        List<Asset> cart = getCart(session);
        cart.removeIf(a -> a.getId().equals(id));
        return "redirect:/assets/cart";
    }

    @PostMapping("/{id}/wishlist")
    public String addToWishlist(@PathVariable Long id, HttpSession session) {
        Asset found = findAssetById(id);
        List<Asset> wishlist = getWishlist(session);

        if (found != null && wishlist.stream().noneMatch(a -> a.getId().equals(id))) {
            wishlist.add(found);
        }

        return "redirect:/assets/" + id;
    }

    @PostMapping("/{id}/wishlist/remove")
    public String removeFromWishlist(@PathVariable Long id, HttpSession session) {
        List<Asset> wishlist = getWishlist(session);
        wishlist.removeIf(a -> a.getId().equals(id));
        return "redirect:/users/home";
    }

    @GetMapping("/cart")
    public String cart(Model model, HttpSession session) {
        List<Asset> cart = getCart(session);
        model.addAttribute("cart", cart);
        model.addAttribute("cartCount", cart.size());
        return "user/cart";
    }

    @PostMapping("/checkout")
    public String checkout(HttpSession session) {
        List<Asset> cart = getCart(session);
        List<Asset> purchases = getPurchases(session);

        for (Asset asset : cart) {
            boolean alreadyPurchased = purchases.stream()
                    .anyMatch(a -> a.getId().equals(asset.getId()));

            if (!alreadyPurchased) {
                purchases.add(asset);
            }
        }

        cart.clear();
        session.setAttribute("cart", cart);
        session.setAttribute("purchases", purchases);

        return "redirect:/users/home";
    }

    private List<Asset> getCart(HttpSession session) {
        List<Asset> cart = (List<Asset>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    private List<Asset> getWishlist(HttpSession session) {
        List<Asset> wishlist = (List<Asset>) session.getAttribute("wishlist");
        if (wishlist == null) {
            wishlist = new ArrayList<>();
            session.setAttribute("wishlist", wishlist);
        }
        return wishlist;
    }

    private List<Asset> getPurchases(HttpSession session) {
        List<Asset> purchases = (List<Asset>) session.getAttribute("purchases");
        if (purchases == null) {
            purchases = new ArrayList<>();
            session.setAttribute("purchases", purchases);
        }
        return purchases;
    }

    private Asset findAssetById(Long id) {
        return sampleAssets().stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    private List<Asset> sampleAssets() {
        License personal = new License("Personal",
                "Use in personal projects. Not for resale or redistribution.");

        License commercial = new License("Commercial",
                "Use in games, renders, and commercial products. No resale of raw files.");

        return List.of(
                new Asset(1L, "Plant", 12.00, "FBX", personal),
                new Asset(2L, "Window Frame", 1500.00, "GLB", commercial),
                new Asset(3L, "Laptop Model ", 1000.00, "OBJ", commercial),
                new Asset(4L, "Phone Model", 800.00, "OBJ", personal),
                new Asset(5L, "Desk Lamp", 16.00, "FBX", commercial),
                new Asset(6L, "Sofa", 600.00, "GLB", personal)
        );
    }
}
