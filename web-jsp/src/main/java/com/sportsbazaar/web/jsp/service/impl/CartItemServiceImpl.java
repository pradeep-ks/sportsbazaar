package com.sportsbazaar.web.jsp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sportsbazaar.persistence.model.Cart;
import com.sportsbazaar.persistence.model.CartItem;
import com.sportsbazaar.persistence.repository.CartItemRepository;
import com.sportsbazaar.web.jsp.service.CartItemService;

@Service("cartItemService")
@Transactional(readOnly = true)
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    @Transactional
    public CartItem save(CartItem cartItem) {
	return this.cartItemRepository.save(cartItem);
    }

    @Override
    @Transactional
    public void delete(CartItem cartItem) {
	this.cartItemRepository.deleteById(cartItem.getId());
	System.out.println("Item with id " + cartItem.getId() + " deleted!");
    }

    @Override
    @Transactional
    public void deleteAllByCart(Cart cart) {
	// cart.getCartItems().forEach(cartItem -> delete(cartItem));
	this.cartItemRepository.deleteAllByCart(cart.getId());
    }

    @Override
    public CartItem findOne(Long itemId) {
	CartItem cartItem = this.cartItemRepository.findById(itemId)
		.orElseThrow(() -> new RuntimeException("Cart item with id not found"));
	return cartItem;
    }

}
