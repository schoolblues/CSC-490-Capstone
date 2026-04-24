package com.backend.CreativityMarket.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.CreativityMarket.Marketplace.Category;
import java.util.List;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {
    List<Asset> findByCategory(String category);
    List<Asset> findByCreatorName(String creatorName);
    List<Asset> findByCreator(User creator);
    List<Asset> findByCategoryEntity(Category categoryEntity);

    @Query("SELECT a FROM Asset a WHERE LOWER(a.tags) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Asset> findByTagKeyword(@Param("keyword") String keyword);
}
