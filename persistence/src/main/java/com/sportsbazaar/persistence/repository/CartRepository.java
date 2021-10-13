package com.sportsbazaar.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sportsbazaar.persistence.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

}
