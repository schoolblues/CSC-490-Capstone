package com.backend.CreativityMarket.Marketplace;

import com.backend.CreativityMarket.User.Asset;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Parent order
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @JsonIgnoreProperties("items")
    private Order order;

    // Purchased asset
    @ManyToOne
    @JoinColumn(name = "asset_id", nullable = false)
    private Asset asset;

    // Snapshot price at time of purchase
    @Column(nullable = false)
    private Double priceAtPurchase;

    @Column(nullable = false)
    private Integer quantity;
}