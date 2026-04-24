package com.backend.CreativityMarket.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishlistItemRepository extends JpaRepository<WishlistItem, Long> {
    List<WishlistItem> findByUser(User user);
    Optional<WishlistItem> findByUserAndAsset(User user, Asset asset);
    boolean existsByUserAndAsset(User user, Asset asset);
    void deleteByUserAndAsset(User user, Asset asset);
}
