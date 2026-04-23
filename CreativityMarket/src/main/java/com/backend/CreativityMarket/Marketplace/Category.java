package com.backend.CreativityMarket.Marketplace;

import com.backend.CreativityMarket.User.Asset;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String name;

    @NotBlank
    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "categoryEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("category")
    private List<Asset> assets;
}
