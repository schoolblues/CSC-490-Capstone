package com.backend.CreativityMarket.Marketplace;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository orderRepository;

    // Get orders for a user
    @GetMapping("/user/{userId}")
    public List<Order> getUserOrders(@PathVariable Long userId) {
        return orderRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    // Get single order
    @GetMapping("/{orderId}")
    public Order getOrder(@PathVariable Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}