package com.backend.CreativityMarket.Cart;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartService {
    
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUser_Id(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user id: " + userId));
    }

    public List<CartItem> getCartItems(Long cartId) {
        return cartItemRepository.findByCartId(cartId);
    }

    public List<CartItem> getCartItemsByUserId(Long userId) {
        Cart cart = getCartByUserId(userId);
        return getCartItems(cart.getId());
    }
}
