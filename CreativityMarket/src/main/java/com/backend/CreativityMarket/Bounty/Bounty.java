package com.backend.CreativityMarket.Bounty;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Column(nullable = false)
    private String description;

    @NotNull
    @Column(nullable = false)
    private Double reward;

    @NotBlank
    @Column(nullable = false)
    private String status;

    @NotNull
    @Column(name = "created_by",nullable = false)
    private Long createdBy;

    @Column(name = "assigned_to")
    private Long assignedTo;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime createdAt;

    public Bounty(@NotBlank String title,
                    @NotBlank String description,
                    @NotNull Double reward,
                    @NotBlank String status,
                    @NotNull Long createdBy,
                    Long assignedTo) {
        this.title = title;
        this.description = description;
        this.reward = reward;
        this.status = status;
        this.createdBy = createdBy;
        this.assignedTo = assignedTo;
        this.createdAt = LocalDateTime.now();
    }
}
