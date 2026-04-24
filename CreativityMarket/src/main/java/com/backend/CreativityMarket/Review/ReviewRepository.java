package com.backend.CreativityMarket.Review;

import com.backend.CreativityMarket.User.Asset;
import com.backend.CreativityMarket.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByAssetOrderByCreatedAtDesc(Asset asset);
    List<Review> findByUser(User user);
    List<Review> findAllByOrderByCreatedAtDesc();
    List<Review> findByFlaggedTrueOrderByCreatedAtDesc();
    boolean existsByAssetAndUser(Asset asset, User user);
}
