package com.backend.CreativityMarket.Marketplace;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.backend.CreativityMarket.User.User;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private User getUser(HttpSession session) {
        Object obj = session.getAttribute("user");

        if(obj instanceof User user) {
            return user;
        }

        throw new RuntimeException("User not logged in");
    }

    // Show the current user's cart
    @GetMapping
    public String showCart(Model model, HttpSession session) {
        User user = getUser(session);

        Cart cart = cartService.getOrCreateCart(user);
        List<CartItem> items = cartService.getCartItemsByUser(user);

        // CartItem only has assetId (Long), not an Asset object
        // so we calculate subtotal based on quantity alone for now
        double subtotal = cartService.calculateCartTotal(user);

        model.addAttribute("cart", cart);
        model.addAttribute("items", items);
        model.addAttribute("subtotal", subtotal);

        return "marketplace/cart";
    }

    // Add an item to the cart
    @PostMapping("/add")
    public String addToCart(HttpSession session,
                            @RequestParam Long assetId,
                            @RequestParam(defaultValue = "1") int quantity) {

        User user = getUser(session);

        cartService.addItem(user, assetId, quantity);

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
