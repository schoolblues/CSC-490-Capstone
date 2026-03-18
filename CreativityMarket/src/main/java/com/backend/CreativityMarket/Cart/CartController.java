package com.backend.CreativityMarket.Cart;

import com.backend.CreativityMarket.User.User;
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

    // Show the current user's cart
    @GetMapping
    public String showCart(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/signin";
        Long userId = user.getId();

        Cart cart = cartService.createCartIfNotExists(userId);
        List<CartItem> items = cartService.getCartItemsByUserId(userId);

        double subtotal = items.stream()
                .mapToDouble(item -> item.getAsset().getPrice() * item.getQuantity())
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
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/signin";
        cartService.addItem(user.getId(), assetId, quantity);
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
