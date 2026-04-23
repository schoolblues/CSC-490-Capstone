package com.backend.CreativityMarket.Marketplace;

import com.backend.CreativityMarket.User.User;
import com.backend.CreativityMarket.User.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private final UserService userService;

    @GetMapping
    public String userOrders(HttpSession session, Model model) {

        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/signin";
        }

        User user = userService.getUserById(userId);

        List<Order> orders = orderRepository.findByUserIdOrderByCreatedAtDesc(user.getId());

        model.addAttribute("orders", orders);

        return "marketplace/orders";
    }
}