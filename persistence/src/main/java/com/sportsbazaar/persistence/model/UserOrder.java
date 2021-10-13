package com.sportsbazaar.persistence.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_orders")
public class UserOrder extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @Column(name = "order_date")
    private LocalDateTime orderDate;

    public Cart getCart() {
	return cart;
    }

    public void setCart(Cart cart) {
	this.cart = cart;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
	return String.format("UserOrder [cart=%s, user=%s, orderDate=%s]", cart, user, orderDate);
    }
}
