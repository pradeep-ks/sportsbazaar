package com.sportsbazaar.web.jsp.service;

import java.security.Principal;

import com.sportsbazaar.persistence.model.Cart;

public interface CartService {

    Cart saveOrUpdate(Cart cart);
    
    boolean addToCart(Long productId, Principal principal);

    void emptyCart(Long cartId);
}
