package com.backend.CreativityMarket.Bounty;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bounties")
@RequiredArgsConstructor
public class BountyApiController {

    private final BountyService bountyService;

    // Get all bounties
    @GetMapping
    public List<Bounty> getAllBounties() {
        return bountyService.getAllBounties();
    }

    // Get open bounties
    @GetMapping("/open")
    public List<Bounty> getOpenBounties() {
        return bountyService.getOpenBounties();
    }

    // Get bounty by ID
    @GetMapping("/{id}")
    public Bounty getBountyById(@PathVariable Long id) {
        return bountyService.getBountyById(id);
    }

    // Get bounties assigned to a specific user
    @GetMapping("/user/{userId}")
    public List<Bounty> getBountiesForUser(@PathVariable Long userId) {
        return bountyService.getBountiesForUser(userId);
    }
}