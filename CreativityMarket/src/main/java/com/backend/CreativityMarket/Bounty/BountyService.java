package com.backend.CreativityMarket.Bounty;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BountyService {
    
    private final BountyRepository bountyRepository;

    public List<Bounty> getAllBounties() {
        return bountyRepository.findAll();
    }

    public List<Bounty> getOpenBounties() {
        return bountyRepository.findByStatus("OPEN");
    }

    public List<Bounty> getBountiesForUser(Long userId) {
        return bountyRepository.findByAssignedTo(userId);
    }

    public Bounty getBountyById(Long id) {
    return bountyRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Bounty not found with id: " + id));
}
}
