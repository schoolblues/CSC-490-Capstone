package com.backend.CreativityMarket.Marketplace;

import com.backend.CreativityMarket.User.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Who placed the order
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Order items snapshot
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("order")
    private List<OrderItem> items = new ArrayList<>();

    @Column(nullable = false)
    private Double total;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;

    private LocalDateTime createdAt = LocalDateTime.now();
}
