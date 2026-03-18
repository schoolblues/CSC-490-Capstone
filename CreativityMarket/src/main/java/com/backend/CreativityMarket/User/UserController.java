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

        model.addAttribute("user", user);
        model.addAttribute("purchases", purchases);
        model.addAttribute("wishlist", wishlist);

        model.addAttribute("purchasesCount", purchases.size());
        model.addAttribute("wishlistCount", wishlist.size());
        model.addAttribute("downloadsCount", purchases.size());
        model.addAttribute("cartCount", cart.size());

        return List.of(
                new Asset(1L, "Modern Chair", 12.00, "/images/apple.png", personal),
                new Asset(2L, "Stylized Tree Set", 10.00, "/images/banana.png", commercial),
                new Asset(3L, "Sci-Fi Door", 15.00, "/images/orange.webp", commercial)
        );
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
