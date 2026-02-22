package com.backend.CreativityMarket.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/assets")
public class AssetController {

    @GetMapping("/{id}")
    public String asset(@PathVariable Long id, Model model) {
        Asset found = sampleAssets().stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (found == null) {
            return "redirect:/users/home";
        }

        model.addAttribute("asset", found);
        return "user/asset"; 
    }

    private List<Asset> sampleAssets() {
        License personal = new License("Personal",
                "Allowed for personal projects. Do not resell or redistribute the raw file.");

        License commercial = new License("Commercial",
                "Allowed for commercial games/renders. Do not resell the raw model or use it for AI training.");

        return List.of(
                new Asset(1L, "Modern Chair", 12.00, "FBX", personal),
                new Asset(2L, "Stylized Tree Set", 10.00, "GLB", commercial),
                new Asset(3L, "Sci-Fi Door", 15.00, "OBJ", commercial)
        );
    }
    @GetMapping("/new")
public String newAsset() {
    return "user/new";
}
}
