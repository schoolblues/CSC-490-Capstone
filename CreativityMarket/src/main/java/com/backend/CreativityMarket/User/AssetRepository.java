package com.backend.CreativityMarket.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//TODO: placeholder
@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {
}
