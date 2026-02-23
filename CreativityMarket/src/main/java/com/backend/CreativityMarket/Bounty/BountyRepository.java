package com.backend.CreativityMarket.Bounty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BountyRepository extends JpaRepository<Bounty, Long> {
    
    List<Bounty> findByStatus(String status);

    List<Bounty> findByAssignedTo(Long userId);
}
