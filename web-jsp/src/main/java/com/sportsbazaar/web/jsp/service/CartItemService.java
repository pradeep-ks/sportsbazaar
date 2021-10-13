package com.sportsbazaar.web.jsp.service;

import com.sportsbazaar.persistence.model.Cart;
import com.sportsbazaar.persistence.model.CartItem;

public interface CartItemService {

    CartItem save(CartItem cartItem);
    
    void delete(CartItem cartItem);
    
    void deleteAllByCart(Cart cart);

    CartItem findOne(Long itemId);
}
