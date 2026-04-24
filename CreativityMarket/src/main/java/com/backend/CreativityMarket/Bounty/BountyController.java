package com.backend.CreativityMarket.Bounty;

import com.backend.CreativityMarket.User.UserRepository;
import com.backend.CreativityMarket.User.User;
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

        model.addAttribute("bounty", bountyService.getBountyById(id));

        return "bounty/bounty-details"; // template: /templates/bounty/bounty-details.ftl
    }

    @GetMapping("/new")
    public String createBounty(HttpSession session, Model model) {
        
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/signin";

        model.addAttribute("bounty", new Bounty());
        model.addAttribute("user", user);

        return "bounty/create-bounty";
    }

    @PostMapping("/new")
    public String createBounty(@ModelAttribute Bounty bounty, HttpSession session) {

        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/signin";

        bountyService.createBounty(bounty, user);

        return "redirect:/bounties";
    }

    @PostMapping("/{id}/apply")
    public String applyToBounty(@PathVariable Long id, HttpSession session) {

        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/signin";

        bountyService.claimBounty(id, user);

        return "redirect:/bounties/" + id;
    }

    @PostMapping("{id}/complete")
    public String completeBounty(@PathVariable Long id, HttpSession session) {

        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/signin";

        bountyService.completeBounty(id, user);

        return "redirect:/bounties/" + id;
    }

    @GetMapping("/me")
    public String myBounties(HttpSession session, Model model) {

        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/signin";

        model.addAttribute("createdBounties", bountyService.getBountiesCreatedByUser(user));
        model.addAttribute("assignedBounties", bountyService.getBountiesForUser(user));

        return "bounty/my-bounties";
    }     

    @GetMapping("/browse")
    public String browseBounties(
            @RequestParam(required = false) String q,
            @RequestParam(required = false, defaultValue = "newest") String sort,
            Model model,
            @SessionAttribute(value = "user", required = false) User user
    ) {

        List<Bounty> bounties = bountyService.searchAndSort(q, sort);

        model.addAttribute("bounties", bounties);
        model.addAttribute("user", user);
        model.addAttribute("query", q);
        model.addAttribute("sort", sort);

        return "bounty/browse-bounties";
    }
}