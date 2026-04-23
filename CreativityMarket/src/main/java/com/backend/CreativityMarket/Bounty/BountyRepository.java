package com.backend.CreativityMarket.Bounty;

import com.backend.CreativityMarket.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

    @Repository
    public interface BountyRepository extends JpaRepository<Bounty, Long> {
        // main board filtering
        List<Bounty> findByStatusAndAssignedToIsNull(BountyStatus status);
        // user filtering
        List<Bounty> findByAssignedTo(User user);
        List<Bounty> findByCreatedBy(User user);
        //reward filtering
        List<Bounty> findByRewardBetween(Double min, Double max);
        //admin filtering
        List<Bounty> findByStatus(BountyStatus status);
        List<Bounty> findByCreatedAtAfter(LocalDateTime date);
    }
