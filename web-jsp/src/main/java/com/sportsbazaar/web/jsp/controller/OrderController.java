package com.sportsbazaar.web.jsp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sportsbazaar.persistence.model.Cart;
import com.sportsbazaar.persistence.model.User;
import com.sportsbazaar.persistence.model.UserOrder;
import com.sportsbazaar.web.jsp.service.CartService;
import com.sportsbazaar.web.jsp.service.UserOrderService;

@Controller
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
    
    @Autowired
    private CartService cartService;
    
    @Autowired
    private UserOrderService userOrderService;
    
    @RequestMapping("/checkout")
    public String placeOrder(@RequestParam("cartId") Long cartId) {
	LOGGER.info("Placing order from cart id {}", cartId);
	Long orderId = placeUserOrder(cartId);
	LOGGER.info("Order id {} generated", orderId);
	return "redirect:/processOrder?orderId=" + orderId;
    }

    private Long placeUserOrder(Long cartId) {
	Cart cart = this.cartService.findById(cartId);
	User user = cart.getUser();
	UserOrder userOrder = new UserOrder();
	userOrder.setCart(cart);
	userOrder.setUser(user);
	userOrder = this.userOrderService.save(userOrder);
	return userOrder.getId();
    }
}
