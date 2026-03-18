package com.backend.CreativityMarket.User;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    public AssetController(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
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
        return "user/detailedItemView";
    }
}
