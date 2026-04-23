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

     @GetMapping("/assigned/{userId}")
    public List<Bounty> getAssigned(@PathVariable Long userId) {
        return bountyService.getBountiesForUserById(userId);
    }

    @GetMapping("/created/{userId}")
    public List<Bounty> getCreated(@PathVariable Long userId) {
        return bountyService.getBountiesCreatedByUserId(userId);
    }
}