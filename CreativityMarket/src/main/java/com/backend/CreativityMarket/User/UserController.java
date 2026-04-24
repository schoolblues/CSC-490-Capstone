package com.backend.CreativityMarket.User;

import com.backend.CreativityMarket.Marketplace.OrderItem;
import com.backend.CreativityMarket.Marketplace.OrderRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UserController {

    private final OrderRepository orderRepository;

    public UserController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @SuppressWarnings("unchecked")
    @GetMapping("/home")
    public String userHome(HttpSession session, Model model) {

        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/signin";
        }

        List<OrderItem> purchases = new ArrayList<>();
        if (user.getId() != null) {
            purchases = orderRepository.findByUserIdOrderByCreatedAtDesc(user.getId())
                    .stream()
                    .flatMap(order -> order.getItems().stream())
                    .collect(Collectors.toList());
        }

        List<Asset> wishlist = (List<Asset>) session.getAttribute("wishlist");
        if (wishlist == null) {
            wishlist = new ArrayList<>();
            session.setAttribute("wishlist", wishlist);
        }

        model.addAttribute("user", user);
        model.addAttribute("purchases", purchases);
        model.addAttribute("wishlist", wishlist);
        model.addAttribute("purchasesCount", purchases.size());
        model.addAttribute("wishlistCount", wishlist.size());
        model.addAttribute("downloadsCount", purchases.size());

        return "user/user-home";
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