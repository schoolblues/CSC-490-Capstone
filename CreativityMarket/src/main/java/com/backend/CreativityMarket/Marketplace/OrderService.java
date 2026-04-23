package com.backend.CreativityMarket.Marketplace;

import com.backend.CreativityMarket.User.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;

    public Order createOrderFromCart(User user) {

        List<CartItem> cartItems = cartService.getCartItemsByUser(user);

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Order order = new Order();
        order.setUser(user);

        double total = 0;

        for (CartItem item : cartItems) {

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setAsset(item.getAsset());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPriceAtPurchase(item.getAsset().getPrice());

            order.getItems().add(orderItem);

            total += item.getAsset().getPrice() * item.getQuantity();
        }

        order.setTotal(total);
        order.setStatus(OrderStatus.PENDING);

        Order savedOrder = orderRepository.save(order);

        cartService.clearCart(user);

        return savedOrder;
    }
}