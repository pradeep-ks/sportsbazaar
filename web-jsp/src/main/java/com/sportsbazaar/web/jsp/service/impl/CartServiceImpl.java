package com.sportsbazaar.web.jsp.service.impl;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sportsbazaar.persistence.model.Cart;
import com.sportsbazaar.persistence.model.CartItem;
import com.sportsbazaar.persistence.model.Product;
import com.sportsbazaar.persistence.model.User;
import com.sportsbazaar.persistence.repository.CartRepository;
import com.sportsbazaar.web.jsp.service.CartItemService;
import com.sportsbazaar.web.jsp.service.CartService;
import com.sportsbazaar.web.jsp.service.ProductService;
import com.sportsbazaar.web.jsp.service.UserService;

@Service
@Transactional(readOnly = true)
public class CartServiceImpl implements CartService {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart saveOrUpdate(Cart cart) {
	return null;
    }

    @Override
    public boolean addToCart(Long productId, Principal principal) {
	User currentUser = this.userService.findByUsername(principal.getName());
	Cart cart = currentUser.getCart();
	Product product = this.productService.findById(productId);

	List<CartItem> cartItems = cart.getCartItems();
	if (!cartItems.isEmpty()) {
	    for (CartItem item : cartItems) {
		if (productId == item.getProduct().getId()) {
		    item.setQuantity(item.getQuantity() + 1);
		    item.setItemTotal(item.getQuantity() * product.getPrice().doubleValue());
		    this.cartItemService.save(item);
		    return true;
		}
	    }
	}
	CartItem cartItem = new CartItem();
	cartItem.setProduct(product);
	cartItem.setQuantity(1);
	cartItem.setItemTotal(cartItem.getQuantity() * product.getPrice().doubleValue());
	cartItem.setCart(cart);
	this.cartItemService.save(cartItem);
	return true;
    }

    @Override
    public void emptyCart(Long cartId) {
	Cart cart = this.cartRepository.findById(cartId)
		.orElseThrow(() -> new RuntimeException("Cart with id = " + cartId + " not found"));
	this.cartItemService.deleteAllByCart(cart);
    }

    @Override
    public Cart findById(Long cartId) {
	return this.cartRepository.findById(cartId)
		.orElseThrow(() -> new RuntimeException("Cart with id = " + cartId + " not found"));
    }

}
