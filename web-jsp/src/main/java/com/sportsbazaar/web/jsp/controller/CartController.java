package com.sportsbazaar.web.jsp.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sportsbazaar.persistence.model.User;
import com.sportsbazaar.web.jsp.service.UserService;

@Controller
public class CartController {

//    @Autowired
//    private CartService cartService;
    
    @Autowired
    private UserService userService;
    
    /*
     * @RequestMapping(value = "/addToCart") public String
     * addToCart(@RequestParam("productId") Long productId, Principal principal,
     * Model model) { boolean result = this.cartService.addToCart(productId,
     * principal); if (result) { model.addAttribute("message",
     * "Product added to cart"); } else { model.addAttribute("errMessage",
     * "Something went wrong!"); } return "shoppingCart"; }
     */
    
    @RequestMapping(value = "/showCart")
    public String showCart(Principal principal, Model model) {
	User user = this.userService.findByUsername(principal.getName());
	model.addAttribute("currentUser", user);
	model.addAttribute("cartId", user.getCart().getId());
	return "shoppingCart";
    }
}
