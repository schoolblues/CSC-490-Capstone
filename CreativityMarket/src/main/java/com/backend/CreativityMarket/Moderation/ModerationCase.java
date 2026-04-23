package com.backend.CreativityMarket.Moderation;

import com.backend.CreativityMarket.User.User;
import com.backend.CreativityMarket.Common.EntityType;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Data;


import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "moderation_cases")
public class ModerationCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @Enumerated(EnumType.STRING)
    private ModerationPriority priority;

    
    @ManyToOne
    private User reportedBy;

    @ManyToOne(optional = false)
    private User reportedUser;

    @Enumerated(EnumType.STRING)
    private EntityType targetType;
    
    private Long entityId;
    
    @Column(columnDefinition = "TEXT")
    private String reason;

    @Enumerated(EnumType.STRING)
    private ModerationStatus status = ModerationStatus.OPEN;

    @ManyToOne
    private User handledBy;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime resolvedAt;
}