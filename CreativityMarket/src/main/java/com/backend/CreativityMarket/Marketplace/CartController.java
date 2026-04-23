package com.backend.CreativityMarket.Marketplace;

import com.backend.CreativityMarket.User.Asset;
import com.backend.CreativityMarket.User.AssetRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.backend.CreativityMarket.User.User;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final AssetRepository assetRepository;

        if(obj instanceof User user) {
            return user;
        }

        throw new RuntimeException("User not logged in");
    }

    @GetMapping
    public String showCart(Model model, HttpSession session) {
        User user = getUser(session);

        cartService.createCartIfNotExists(userId);
        List<CartItem> items = cartService.getCartItemsByUserId(userId);

        List<Asset> cart = items.stream()
                .map(item -> assetRepository.findById(item.getAssetId()).orElse(null))
                .filter(asset -> asset != null)
                .collect(Collectors.toList());

        model.addAttribute("cart", cart);
        model.addAttribute("cartCount", cart.size());

        return "marketplace/cart";
    }

    @PostMapping("/add")
    public String addToCart(HttpSession session,
                            @RequestParam Long assetId,
                            @RequestParam(defaultValue = "1") int quantity) {

        User user = getUser(session);

        cartService.addItem(user, assetId, quantity);

        return "redirect:/cart";
    }

    @PostMapping("/remove/{cartItemId}")
    public String removeFromCart(@PathVariable Long cartItemId) {

        cartService.removeItem(cartItemId);

        return "redirect:/cart";
    }

    @PostMapping("/update/{cartItemId}")
    public String updateQuantity(@PathVariable Long cartItemId,
                                 @RequestParam int quantity) {

        cartService.updateItemQuantity(cartItemId, quantity);

        return "redirect:/cart";
    }
}
