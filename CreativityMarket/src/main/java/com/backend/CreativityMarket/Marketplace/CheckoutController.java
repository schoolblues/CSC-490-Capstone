package com.backend.CreativityMarket.Marketplace;

import com.backend.CreativityMarket.User.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/checkout")
public class CheckoutController {

    private final CartService cartService;
    private final OrderService orderService;

    private User getUser(HttpSession session) {
        Object obj = session.getAttribute("user");
        if (obj instanceof User u && u.getId() != null) return u;
        return null;
    }

    // 🔹 Step 1: Show checkout page
    @GetMapping
    public String checkoutPage(HttpSession session, Model model) {

        User user = getUser(session);
        if (user == null) return "redirect:/signin";

        List<CartItem> items = cartService.getCartItemsByUser(user);

        double subtotal = cartService.calculateCartTotal(user);

        model.addAttribute("items", items);
        model.addAttribute("subtotal", subtotal);
        model.addAttribute("user", user);

        return "marketplace/checkout";
    }

    // 🔹 Step 2: Create order (PENDING payment later)
    @PostMapping("/create")
    public String createOrder(HttpSession session) {

        User user = getUser(session);
        if (user == null) return "redirect:/signin";

        Order order = orderService.createOrderFromCart(user);

        // TODO later: redirect to PayPal instead of marking completed instantly
        return "redirect:/checkout/success?orderId=" + order.getId();
    }

    // 🔹 Step 3: Success page
    @GetMapping("/success")
    public String success(@RequestParam Long orderId, Model model) {
        model.addAttribute("orderId", orderId);
        return "marketplace/checkout-success";
    }

    // 🔹 Step 4: Cancel page (for PayPal later)
    @GetMapping("/cancel")
    public String cancel() {
        return "marketplace/checkout-cancel";
    }
}