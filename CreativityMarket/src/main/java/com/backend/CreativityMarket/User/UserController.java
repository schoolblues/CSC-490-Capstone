package com.backend.CreativityMarket.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @GetMapping("/home")
    public String userHome(HttpSession session, Model model) {

      
        User user = (User) session.getAttribute("user");

       
        if (user == null) {
            user = new User();
            user.setName("User");
            user.setEmail("No email on file");
            user.setCreatedAt("2026-02-22");
            user.setRole("CREATOR");
            session.setAttribute("user", user);
        }

        List<Asset> assets = sampleAssets();

        model.addAttribute("user", user);
        model.addAttribute("assets", assets);
        model.addAttribute("assetsCount", assets.size());

        
        model.addAttribute("salesCount", 0);
        model.addAttribute("earnings", 0.00);

        return "user/user-home"; 
    }

    private List<Asset> sampleAssets() {
        License personal = new License("Personal",
                "Allowed for personal projects. Do not resell or redistribute the raw file.");

        License commercial = new License("Commercial",
                "Allowed for commercial games/renders. Do not resell the raw model or use it for AI training.");

        return List.of(
            buildSampleAsset(1L, "Modern Chair", 12.00, "FBX", "/images/apple.png", "a5eb5c78e5a14955802e7eb64b76e1a1", personal),
            buildSampleAsset(2L, "Stylized Tree Set", 10.00, "GLB", "/images/banana.png", "a5eb5c78e5a14955802e7eb64b76e1a1", commercial),
            buildSampleAsset(3L, "Sci-Fi Door", 15.00, "OBJ", "/images/orange.webp", "a5eb5c78e5a14955802e7eb64b76e1a1", commercial)
        );
    }

        private Asset buildSampleAsset(Long id,
                       String title,
                       double price,
                       String fileType,
                       String thumbnailUrl,
                       String sketchfabUid,
                       License license) {
        Asset asset = new Asset(id, title, price, thumbnailUrl, license);
        asset.setFileType(fileType);
        asset.setSketchfabUid(sketchfabUid);
        asset.setCreatorName("Creativity Market Studio");
        return asset;
        }

    @GetMapping("/profile/edit")
public String editProfile(HttpSession session, Model model,   
                          @RequestParam(value = "saved", required = false) Boolean saved) {

    User user = (User) session.getAttribute("user");
    if (user == null) return "redirect:/signin";

    model.addAttribute("user", user);
    model.addAttribute("saved", saved != null && saved);

    return "user/edit-profile"; 
}

@PostMapping("/profile/edit")
public String saveProfile(HttpSession session,
                          @RequestParam String name,
                          @RequestParam String email,
                          @RequestParam(required = false) String createdAt) {

    User user = (User) session.getAttribute("user");
    if (user == null) return "redirect:/signin";

    user.setName(name);
    user.setEmail(email);
    user.setCreatedAt(createdAt);

    
    session.setAttribute("user", user);

    return "redirect:/users/profile/edit?saved=true";
}
}
