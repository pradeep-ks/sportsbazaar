package com.sportsbazaar.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sportsbazaar.persistence.model.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM CartItem ci WHERE ci.cart.id = ?1")
    void deleteAllByCart(Long cartId);
    
    @Transactional
    @Modifying
    @Query("DELETE FROM CartItem ci WHERE ci.id = ?1")
    void deleteById(Long itemId);
    
}
