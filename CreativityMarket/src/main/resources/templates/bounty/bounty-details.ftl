package com.backend.CreativityMarket.Bounty;

import com.backend.CreativityMarket.User.User;
import com.backend.CreativityMarket.User.UserRepository;
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
    public String bountyBoard(Model model, @SessionAttribute("userId") Long userId) {
        // Fetch open bounties
        List<Bounty> openBounties = bountyService.getOpenBounties();

        // Optional: get user info for display in navbar or greetings
        User currentUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));

        model.addAttribute("user", currentUser);
        model.addAttribute("bounties", openBounties);  // matches bounty-board.ftl
        return "bounty/bounty-board"; // maps to /templates/bounty/bounty-board.ftl
    }

    // Display details for a specific bounty
    @GetMapping("/{id}")
    public String bountyDetails(@PathVariable Long id, Model model) {
        Bounty bounty = bountyService.getBountyById(id); // make sure this exists in service
        model.addAttribute("bounty", bounty);
        return "bounty/bounty-details"; // template: /templates/bounty/bounty-details.ftl
    }
}