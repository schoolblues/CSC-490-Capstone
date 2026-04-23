package com.backend.CreativityMarket.Marketplace;

import com.backend.CreativityMarket.User.User;
import com.backend.CreativityMarket.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkout")
@RequiredArgsConstructor
public class CheckoutApiController {

    private final OrderService orderService;
    private final UserService userService;

    @PostMapping("/{userId}")
    public Order checkout(@PathVariable Long userId) {

        User user = userService.getUserById(userId);

        return orderService.createOrderFromCart(user);
    }
}