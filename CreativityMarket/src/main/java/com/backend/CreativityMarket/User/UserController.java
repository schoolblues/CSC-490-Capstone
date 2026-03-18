package com.backend.CreativityMarket.User;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
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
            user.setRole("USER");
            session.setAttribute("user", user);
        }

        List<Asset> purchases = (List<Asset>) session.getAttribute("purchases");
        if (purchases == null) {
            purchases = new ArrayList<>();
            session.setAttribute("purchases", purchases);
        }

        List<Asset> wishlist = (List<Asset>) session.getAttribute("wishlist");
        if (wishlist == null) {
            wishlist = new ArrayList<>();
            session.setAttribute("wishlist", wishlist);
        }

        List<Asset> cart = (List<Asset>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }

    private List<Asset> sampleAssets() {
        Asset a1 = new Asset();
        a1.setId(1L);
        a1.setTitle("Modern Chair");
        a1.setPrice(12.00);
        a1.setThumbnailUrl("/images/apple.png");
        a1.setLicense("Personal");

        Asset a2 = new Asset();
        a2.setId(2L);
        a2.setTitle("Stylized Tree Set");
        a2.setPrice(10.00);
        a2.setThumbnailUrl("/images/banana.png");
        a2.setLicense("Commercial");

        Asset a3 = new Asset();
        a3.setId(3L);
        a3.setTitle("Sci-Fi Door");
        a3.setPrice(15.00);
        a3.setThumbnailUrl("/images/orange.webp");
        a3.setLicense("Commercial");

        return List.of(a1, a2, a3);
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