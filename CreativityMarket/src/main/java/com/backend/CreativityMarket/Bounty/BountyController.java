package com.backend.CreativityMarket.Bounty;

import com.backend.CreativityMarket.User.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/bounties")
public class BountyController {

    private final BountyService bountyService;
    private final UserRepository userRepository;

    // Display all open bounties on the bounty board
    @GetMapping
    public String bountyBoard(Model model, HttpSession session) {
        List<Bounty> openBounties = bountyService.getOpenBounties();

        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            userRepository.findById(userId).ifPresent(u -> model.addAttribute("user", u));
        }

        model.addAttribute("bounties", openBounties);
        return "bounty/bounty-board";
    }

    // Display details for a specific bounty
    @GetMapping("/{id}")
    public String bountyDetails(@PathVariable Long id, Model model) {
        Bounty bounty = bountyService.getBountyById(id); // make sure this exists in service
        model.addAttribute("bounty", bounty);
        return "bounty/bounty-details"; // template: /templates/bounty/bounty-details.ftl
    }
}