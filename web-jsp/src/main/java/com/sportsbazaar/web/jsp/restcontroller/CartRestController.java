package com.sportsbazaar.web.jsp.restcontroller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sportsbazaar.persistence.model.Cart;
import com.sportsbazaar.persistence.model.CartItem;
import com.sportsbazaar.persistence.model.User;
import com.sportsbazaar.web.jsp.dto.ResponseMessage;
import com.sportsbazaar.web.jsp.service.CartItemService;
import com.sportsbazaar.web.jsp.service.CartService;
import com.sportsbazaar.web.jsp.service.UserService;

@RestController
@RequestMapping("/api/cart")
public class CartRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;
    
    @Autowired
    private CartItemService cartItemService;

    @GetMapping
    public ResponseEntity<Cart> getCart(Principal principal) {
	if (principal != null) {
	    User user = this.userService.findByUsername(principal.getName());
	    return new ResponseEntity<>(user.getCart(), HttpStatus.OK);
	} else {
	    return new ResponseEntity<>(HttpStatus.OK);
	}
    }

    @GetMapping("/add/{prodId}")
    public ResponseEntity<String> addItemToCart(@PathVariable("prodId") Long productId, Principal principal) {
	boolean result = this.cartService.addToCart(productId, principal);
	if (result) {
	    return new ResponseEntity<String>("Product added to cart", HttpStatus.OK);
	} else {
	    return new ResponseEntity<String>("Something went wrong! Could not add product to the cart",
		    HttpStatus.BAD_REQUEST);
	}
    }
    
    @GetMapping("/clear/{cartId}")
    public ResponseEntity<?> clearCart(@PathVariable("cartId") Long cartId) {
	this.cartService.emptyCart(cartId);
	return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @GetMapping("/remove/{itemId}")
    public ResponseEntity<?> removeItem(@PathVariable("itemId") Long itemId) {
	CartItem cartItem = cartItemService.findOne(itemId);
	System.out.println("Item found with id = " + cartItem.getId());
	cartItemService.delete(cartItem);
	return ResponseEntity.ok(new ResponseMessage("Item removed!", true));
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
