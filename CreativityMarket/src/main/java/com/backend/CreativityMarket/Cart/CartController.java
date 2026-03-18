package com.backend.CreativityMarket.Cart;

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
    public String showCart(Model model, @SessionAttribute("userId") Long userId) {
        // Retrieve the cart and items
        Cart cart = cartService.createCartIfNotExists(userId);
        List<CartItem> items = cartService.getCartItemsByUserId(userId);

        // Calculate subtotal
        double subtotal = items.stream()
                .mapToDouble(item -> item.getAsset().getPrice() * item.getQuantity())
                .sum();

        model.addAttribute("cart", cart);
        model.addAttribute("items", items);
        model.addAttribute("subtotal", subtotal);

        return "cart/cart"; // Freemarker template: /templates/cart/cart.ftl
    }

    // Add an item to the cart
    @PostMapping("/add")
    public String addToCart(@SessionAttribute("userId") Long userId,
                            @RequestParam Long assetId,
                            @RequestParam(defaultValue = "1") int quantity) {
        cartService.addItem(userId, assetId, quantity);
        return "redirect:/cart"; // refresh the cart page
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
