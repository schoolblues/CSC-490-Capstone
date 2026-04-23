package com.backend.CreativityMarket.Admin;

import com.backend.CreativityMarket.User.UserRepository;
import com.backend.CreativityMarket.Marketplace.CategoryRepository;
import com.backend.CreativityMarket.Bounty.BountyRepository;
import com.backend.CreativityMarket.Moderation.ModerationCaseRepository;
import com.backend.CreativityMarket.User.AssetRepository;
import com.backend.CreativityMarket.AuditLog.AuditLogRepository;
import com.backend.CreativityMarket.Moderation.ModerationStatus;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminReportService {

    private final UserRepository userRepository;
    private final BountyRepository bountyRepository;
    private final CategoryRepository categoryRepository;
    private final ModerationCaseRepository moderationCaseRepository;
    private final AuditLogRepository auditLogRepository;
    private final AssetRepository assetRepository;

    // =========================
    // BASIC COUNTS (DASHBOARD)
    // =========================

    public long countUsers() {
        return userRepository.count();
    }

    public long countBounties() {
        return bountyRepository.count();
    }

    public long countCategories() {
        return categoryRepository.count();
    }

    public long countOpenCases() {
        return moderationCaseRepository.countByStatus(ModerationStatus.OPEN);
    }

    public long countResolvedCases() {
        return moderationCaseRepository.countByStatus(ModerationStatus.RESOLVED);
    }

    // =========================
    // ACTIVITY DATA (CHARTS)
    // =========================

    public List<Object[]> getActionsPerDay() {
        return auditLogRepository.countActionsPerDay()
            .stream()
            .map(row -> new Object[]{
                    row[0].toString(),
                    ((Number) row[1]).longValue()
            })
            .toList();
    }

    public List<Object[]> getTopActions() {
        return auditLogRepository.countByAction()
            .stream()
            .map(row -> new Object[]{
                    row[0].toString(),
                    ((Number) row[1]).longValue()
            })
            .toList();
    }

    public List<Object[]> countCasesByStatus() {

        return moderationCaseRepository.countCasesByStatus()
                .stream()
                .map(row -> new Object[]{
                        row[0].toString(),
                        ((Number) row[1]).longValue()
                })
                .toList();
    }

    public List<Object[]> countUsersByRole() {

        return userRepository.countUsersByRole()
                .stream()
                .map(row -> new Object[]{
                        row[0].toString(),
                        ((Number) row[1]).longValue()
                })
                .toList();
    }

    public List<Object[]> countAssetsAndBounties() {

        long assets = assetRepository.count();
        long bounties = bountyRepository.count();

        return List.of(
                new Object[]{"ASSETS", assets},
                new Object[]{"BOUNTIES", bounties}
        );
    }
}