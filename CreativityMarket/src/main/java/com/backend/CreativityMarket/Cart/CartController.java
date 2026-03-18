package com.backend.CreativityMarket.Cart;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    private Long getUserId(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            userId = 0L;
            session.setAttribute("userId", userId);
        }
        return userId;
    }

    // Show the current user's cart
    @GetMapping
    public String showCart(Model model, HttpSession session) {
        Long userId = getUserId(session);

        Cart cart = cartService.createCartIfNotExists(userId);
        List<CartItem> items = cartService.getCartItemsByUserId(userId);

        // CartItem only has assetId (Long), not an Asset object
        // so we calculate subtotal based on quantity alone for now
        double subtotal = items.stream()
                .mapToDouble(item -> item.getQuantity())
                .sum();

        model.addAttribute("cart", cart);
        model.addAttribute("items", items);
        model.addAttribute("subtotal", subtotal);

        return "user/cart";
    }

    // Add an item to the cart
    @PostMapping("/add")
    public String addToCart(HttpSession session,
                            @RequestParam Long assetId,
                            @RequestParam(defaultValue = "1") int quantity) {
        Long userId = getUserId(session);
        cartService.addItem(userId, assetId, quantity);
        return "redirect:/cart";
    }

    // Remove an item from the cart
    @PostMapping("/remove/{cartItemId}")
    public String removeFromCart(@PathVariable Long cartItemId) {
        cartService.removeItem(cartItemId);
        return "redirect:/cart";
    }

    // Update quantity of a cart item
    @PostMapping("/update/{cartItemId}")
    public String updateQuantity(@PathVariable Long cartItemId,
                                 @RequestParam int quantity) {
        cartService.updateItemQuantity(cartItemId, quantity);
        return "redirect:/cart";
    }
}
