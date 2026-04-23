package com.backend.CreativityMarket.AuditLog;

import com.backend.CreativityMarket.Common.EntityType;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "audit_logs")
public class AuditLog {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuditAction action;

    @NotNull
    @Column(name = "performed_by", nullable = false)
    private Long performedBy;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EntityType targetEntity;

    @Column(name = "target_id")
    private Long targetId;

    @NotNull
    @Column(nullable= false)
    private LocalDateTime timestamp = LocalDateTime.now();
}
