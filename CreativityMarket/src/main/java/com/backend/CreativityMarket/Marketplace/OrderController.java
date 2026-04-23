package com.backend.CreativityMarket.Marketplace;

import com.backend.CreativityMarket.User.User;
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

    @GetMapping
    public String userOrders(HttpSession session, Model model) {

        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/signin";
        }

        List<Order> orders = orderRepository.findByUserIdOrderByCreatedAtDesc(user.getId());

        model.addAttribute("orders", orders);

        return "marketplace/orders";
    }
}