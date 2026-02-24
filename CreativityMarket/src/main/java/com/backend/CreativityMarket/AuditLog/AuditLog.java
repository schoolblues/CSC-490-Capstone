package com.backend.CreativityMarket.AuditLog;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    @Column(nullable = false)
    private String action;

    @NotNull
    @Column(name = "performed_by", nullable = false)
    private Long performedBy;

    @NotBlank
    @Column(name = "target_entity", nullable = false)
    private String targetEntity;

    @Column(name = "target_id")
    private Long targetId;

    @NotNull
    @Column(nullable= false)
    private LocalDateTime timestamp;

    public AuditLog(@NotBlank String action,
                    @NotNull Long performedBy,
                    @NotBlank String targetEntity,
                     Long targetId) {
        this.action = action;
        this.performedBy = performedBy;
        this.targetEntity = targetEntity;
        this.targetId = targetId;
        this.timestamp = LocalDateTime.now();
    }
}
