package com.backend.CreativityMarket.Bounty;

import com.backend.CreativityMarket.User.User;
import com.backend.CreativityMarket.User.UserService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BountyService {
    
    private final BountyRepository bountyRepository;
    private final UserService userService;

    public List<Bounty> getAllBounties() {
        return bountyRepository.findAll();
    }

    public List<Bounty> getOpenBounties() {
        return bountyRepository.findByStatusAndAssignedToIsNull(BountyStatus.OPEN);
    }

    public List<Bounty> getBountiesForUser(User user) {
        return bountyRepository.findByAssignedTo(user);
    }

    public List<Bounty> getBountiesCreatedByUser(User user) {
        return bountyRepository.findByCreatedBy(user);
    }

    public Bounty getBountyById(Long id) {
    return bountyRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Bounty not found with id: " + id));
    }

    @Transactional
    public Bounty createBounty(Bounty bounty, User creator) {
        bounty.setCreatedBy(creator);
        bounty.setStatus(BountyStatus.OPEN);
        return bountyRepository.save(bounty);
    }

    @Transactional
    public void claimBounty(Long bountyId, User user) {
        Bounty bounty = getBountyById(bountyId);

        if (bounty.getStatus() != BountyStatus.OPEN) {
            throw new RuntimeException("Bounty is not open for claiming");
        }

        if (bounty.getAssignedTo() != null) {
            throw new RuntimeException("Bounty is already claimed by another user");
        }

        bounty.setAssignedTo(user);
        bounty.setStatus(BountyStatus.IN_PROGRESS);

        bountyRepository.save(bounty);
    }

    @Transactional
    public void completeBounty(Long bountyId, User user) {
        Bounty bounty = getBountyById(bountyId);

        if (bounty.getStatus() != BountyStatus.IN_PROGRESS) {
            throw new RuntimeException("Bounty is not in progress");
        }

        if (bounty.getAssignedTo() == null || !user.getId().equals(bounty.getAssignedTo().getId())) {
            throw new RuntimeException("Only the assigned user can complete this bounty");
        }

        bounty.setStatus(BountyStatus.COMPLETED);

        bountyRepository.save(bounty);
    }

    public List<Bounty> getBountiesForUserById(Long userId) {
    User user = userService.getUserById(userId);
    return bountyRepository.findByAssignedTo(user);

    }

    public List<Bounty> getBountiesCreatedByUserId(Long userId) {
        User user = userService.getUserById(userId);
        return bountyRepository.findByCreatedBy(user);
    }

    public void deleteBounty(Long bountyId) {
    
        Bounty bounty = bountyRepository.findById(bountyId)
                .orElseThrow(() -> new EntityNotFoundException("Bounty not found: " + bountyId));
    
        bountyRepository.delete(bounty);
    }
    
    public List<Bounty> getRandomOpenBounties(int limit) {
        List<Bounty> openBounties = bountyRepository.findByStatus(BountyStatus.OPEN);

        return openBounties.stream()
                .sorted((a, b) -> Math.random() > 0.5 ? 1 : -1) // simple shuffle
                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<Bounty> searchOpenBounties(String query) {
        List<Bounty> openBounties = bountyRepository.findByStatus(BountyStatus.OPEN);

        if (query == null || query.isBlank()) {
            return openBounties;
        }

        String lowerQuery = query.toLowerCase();

        return openBounties.stream()
                .filter(b ->
                        (b.getTitle() != null && b.getTitle().toLowerCase().contains(lowerQuery)) ||
                        (b.getDescription() != null && b.getDescription().toLowerCase().contains(lowerQuery))
                )
                .collect(Collectors.toList());
    }

    public List<Bounty> getOpenBountiesSorted(String sort) {
        List<Bounty> openBounties = bountyRepository.findByStatus(BountyStatus.OPEN);

        return applySort(openBounties, sort);
    }

    public List<Bounty> searchAndSort(String query, String sort) {
        List<Bounty> results = searchOpenBounties(query);
        return applySort(results, sort);
    }

    private List<Bounty> applySort(List<Bounty> list, String sort) {

        if (sort == null || sort.isBlank()) {
            sort = "newest";
        }

        return switch (sort) {

            case "oldest" ->
                    list.stream()
                            .sorted(Comparator.comparing(Bounty::getCreatedAt))
                            .collect(Collectors.toList());

            case "highest" ->
                    list.stream()
                            .sorted(Comparator.comparing(Bounty::getReward).reversed())
                            .collect(Collectors.toList());

            case "lowest" ->
                    list.stream()
                            .sorted(Comparator.comparing(Bounty::getReward))
                            .collect(Collectors.toList());

            default -> // newest first
                    list.stream()
                            .sorted(Comparator.comparing(Bounty::getCreatedAt).reversed())
                            .collect(Collectors.toList());
        };
    }


}