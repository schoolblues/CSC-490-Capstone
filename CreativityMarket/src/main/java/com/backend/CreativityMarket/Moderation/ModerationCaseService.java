package com.backend.CreativityMarket.Moderation;

import com.backend.CreativityMarket.User.User;
import com.backend.CreativityMarket.Common.EntityType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class ModerationCaseService {

    private final ModerationCaseRepository moderationCaseRepository;

    public ModerationCase flag(User reporter,
                               User reportedUser,
                               EntityType targetType,
                               Long entityId,
                               String reason) {

        ModerationCase mc = new ModerationCase();

        mc.setReportedBy(reporter);
        mc.setReportedUser(reportedUser);
        mc.setReason(reason);
        mc.setTargetType(targetType);
        mc.setEntityId(entityId);
        
        // PRIORITY RULES
        if (reporter.isAdminOrAbove()) {
            mc.setPriority(ModerationPriority.CRITICAL);
        } else if (reporter.isModerator()) {
            mc.setPriority(ModerationPriority.HIGH);
        } else {
            mc.setPriority(ModerationPriority.NORMAL);
        }

        return moderationCaseRepository.save(mc);
    }

    public List<ModerationCase> getAllCasesForAdmin() {
        return moderationCaseRepository.findAllByOrderByPriorityDescCreatedAtAsc();
    }

    public void resolveCase(Long caseId, User resolver) {
        ModerationCase mc = moderationCaseRepository.findById(caseId)
                .orElseThrow(() -> new RuntimeException("Case not found"));

                mc.setStatus(ModerationStatus.RESOLVED);
                mc.setHandledBy(resolver);
                mc.setResolvedAt(LocalDateTime.now());

                moderationCaseRepository.save(mc);
    }

    public void dismissCase(Long caseId, User resolver) {
        ModerationCase mc = moderationCaseRepository.findById(caseId)
                .orElseThrow(() -> new RuntimeException("Case not found"));

                mc.setStatus(ModerationStatus.DISMISSED);
                mc.setHandledBy(resolver);
                mc.setResolvedAt(LocalDateTime.now());

                moderationCaseRepository.save(mc);
    }
}