package com.backend.CreativityMarket.Marketplace;

import com.backend.CreativityMarket.User.User;
import com.backend.CreativityMarket.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartApiController {

    private final CartService cartService;
    private final UserService userService;

    // Get all items in a user's cart
    @GetMapping("/user/{userId}/items")
    public List<CartItem> getCartItemsByUser(@PathVariable Long userId) {

        User user = userService.getUserById(userId);

        return cartService.getCartItemsByUser(user);
    }

    // Add an item to the user's cart
    @PostMapping("/user/{userId}/items")
    public CartItem addItem(
            @PathVariable Long userId,
            @RequestParam Long assetId,
            @RequestParam int quantity
    ) {
        User user = userService.getUserById(userId);

        return cartService.addItem(user, assetId, quantity);
    }

    // Update quantity of an existing cart item
    @PutMapping("/items/{cartItemId}")
    public void updateItemQuantity(
            @PathVariable Long cartItemId,
            @RequestParam int quantity
    ) {
        cartService.updateItemQuantity(cartItemId, quantity);
    }

    // Remove an item from the cart
    @DeleteMapping("/items/{cartItemId}")
    public void removeItem(@PathVariable Long cartItemId) {
        cartService.removeItem(cartItemId);
    }
}
