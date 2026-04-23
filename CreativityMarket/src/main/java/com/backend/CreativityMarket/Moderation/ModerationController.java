package com.backend.CreativityMarket.Moderation;

import com.backend.CreativityMarket.User.User;
import com.backend.CreativityMarket.User.UserRepository;
import com.backend.CreativityMarket.Common.EntityType;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/moderation")
@RequiredArgsConstructor
public class ModerationController {

    private final ModerationCaseService moderationCaseService;
    private final UserRepository userRepository;

   @PostMapping("/flag")
    public ModerationCase flag(@RequestParam Long reportedUserId,
                               @RequestParam(required = false) EntityType type,
                               @RequestParam(required = false) Long entityId,
                               @RequestParam String reason,
                               HttpSession session) {
                            
        User reporter = (User) session.getAttribute("user");
        User reportedUser = userRepository.findById(reportedUserId)
                .orElseThrow(() -> new RuntimeException("Reported user not found"));
                            
        if (reporter == null) {
            throw new RuntimeException("Not authenticated");
        }
    
        return moderationCaseService.flag(reporter, reportedUser, type, entityId, reason);
    }
}