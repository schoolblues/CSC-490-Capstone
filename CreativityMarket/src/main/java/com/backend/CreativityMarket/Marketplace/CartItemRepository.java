package com.backend.CreativityMarket.Marketplace;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.CreativityMarket.User.User;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByCartUser(User user);
}
