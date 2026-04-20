package com.backend.CreativityMarket.Cart;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import com.backend.CreativityMarket.User.Asset;
import com.backend.CreativityMarket.User.AssetRepository;
import com.backend.CreativityMarket.User.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartService {
    
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository; 
    private final AssetRepository assetRepository;

    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user id: " + userId));
    }

    public List<CartItem> getCartItems(Long cartId) {
        return cartItemRepository.findByCartId(cartId);
    }

    public List<CartItem> getCartItemsByUserId(Long userId) {
        Cart cart = getCartByUserId(userId);
        return getCartItems(cart.getId());
    }

    @Transactional
    public Cart createCartIfNotExists(Long userId) {
        return cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    userRepository.findById(userId)
                            .orElseThrow(() -> new RuntimeException("User not found: " + userId));

                    Cart cart = new Cart();
                    cart.setUserId(userId);
                    return cartRepository.save(cart);
                });
    }
    
    @Transactional
    public CartItem addItem(Long userId, Long assetId, int quantity) {
        Cart cart = createCartIfNotExists(userId);
    
        // Fetch the Asset from the repository
        assetRepository.findById(assetId)
                .orElseThrow(() -> new RuntimeException("Asset not found: " + assetId));
    
        // Check if the item already exists in the cart
        return cart.getItems().stream()
                .filter(item -> item.getAssetId().equals(assetId))
                .findFirst()
                .map(item -> {
                    item.setQuantity(item.getQuantity() + quantity);
                    return cartItemRepository.save(item);
                })
                .orElseGet(() -> {
                    CartItem newItem = new CartItem();
                    newItem.setCart(cart);
                    newItem.setAssetId(assetId);
                    newItem.setQuantity(quantity);
                    cart.getItems().add(newItem);
                    return cartItemRepository.save(newItem);
                });
    }
    
    @Transactional
    public void removeItem(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }
    
    @Transactional
    public void updateItemQuantity(Long cartItemId, int quantity) {
        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("CartItem not found: " + cartItemId));
        item.setQuantity(quantity);
        cartItemRepository.save(item);
    }
}
