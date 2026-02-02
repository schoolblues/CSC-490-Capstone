package com.backend.CreativityMarket.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @GetMapping("/home")
    public String userHome(HttpSession session, Model model) {
        return "user/user-home";
    }

    @GetMapping("/asset/{id}")
    public String asset(@PathVariable Long id, Model model) {
        Asset found = sampleAssets().stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(null);

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
}
