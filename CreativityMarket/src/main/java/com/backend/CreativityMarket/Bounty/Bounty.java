package com.backend.CreativityMarket.Bounty;

import java.time.LocalDateTime;
import com.backend.CreativityMarket.User.User;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Positive;

@Entity
@Data
@NoArgsConstructor
@Table(name = "bounties")
public class Bounty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @NotBlank
    @Column(length = 2000, nullable = false)
    private String description;

    @NotNull
    @Positive
    @Column(nullable = false)
    private Double reward;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BountyStatus status = BountyStatus.OPEN;

    @ManyToOne
    @JoinColumn(name = "created_by",nullable = false)
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "assigned_to")
    private User assignedTo;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}