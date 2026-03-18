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

    @GetMapping("/{id}")
    public String asset(@PathVariable Long id, Model model) {
        Asset found = assetRepository.findById(id).orElse(null);

        if (found == null) {
            return "redirect:/";
        }

        model.addAttribute("asset", found);
        return "user/asset";
    }
}
