package com.backend.CreativityMarket.User;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String asset(@PathVariable Long id, Model model) {
        Asset found = assetRepository.findById(id).orElse(null);

        if (found == null) {
            return "redirect:/";
        }

        model.addAttribute("asset", found);
        return "user/detailedItemView";
    }
}
