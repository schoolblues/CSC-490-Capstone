package com.backend.CreativityMarket.Marketplace;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import com.backend.CreativityMarket.User.Asset;
import com.backend.CreativityMarket.User.AssetRepository;
import com.backend.CreativityMarket.User.User;
import com.backend.CreativityMarket.User.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final AssetRepository assetRepository;
    private final UserRepository userRepository;

    public Cart getOrCreateCart(User user) {
        User managedUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("User not found: " + user.getId()));
        return cartRepository.findByUserId(managedUser.getId())
                            .orElseGet(() -> {
                                Cart cart = new Cart();
                                cart.setUser(managedUser);
                                cart.setItems(new HashSet<>());
                                return cartRepository.save(cart);
                            });
    }

    public List<CartItem> getCartItemsByUser(User user) {
        return cartRepository.findByUserIdWithItems(user.getId())
                            .map(cart -> new ArrayList<CartItem>(cart.getItems()))
                            .orElseGet(ArrayList::new);
    }

    @Transactional
    public CartItem addItem(User user, Long assetId, int quantity) {

        Cart cart = getOrCreateCart(user);

        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() ->
                        new RuntimeException("Asset not found: " + assetId));

        CartItem existingItem = cart.getItems().stream()
                .filter(item -> item.getAsset().getId().equals(assetId))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            cartRepository.save(cart);
            return existingItem;
        }

        CartItem newItem = new CartItem();
        newItem.setCart(cart);
        newItem.setAsset(asset);
        newItem.setQuantity(quantity);

        cart.getItems().add(newItem);
        cartRepository.save(cart);

        return newItem;
    }

    @Transactional
    public void removeItem(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    @Transactional
    public void updateItemQuantity(Long cartItemId, int quantity) {
        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() ->
                        new RuntimeException("CartItem not found: " + cartItemId));

        item.setQuantity(quantity);
        cartItemRepository.save(item);
    }

    public double calculateCartTotal(User user) {
        return getCartItemsByUser(user)
                .stream()
                .mapToDouble(i -> i.getAsset().getPrice() * i.getQuantity())
                .sum();
    }

    public void clearCart(User user) {
        Cart cart = getOrCreateCart(user);
        cart.getItems().clear();
        cartRepository.save(cart);
    }
}