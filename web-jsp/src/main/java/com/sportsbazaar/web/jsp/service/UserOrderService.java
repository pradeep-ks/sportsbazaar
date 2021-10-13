package com.sportsbazaar.web.jsp.service;

import java.util.List;

import com.sportsbazaar.persistence.model.UserOrder;

public interface UserOrderService {

    UserOrder save(UserOrder order);
    
    List<UserOrder> findAllOrdersByUserId(Long userId);
    
    UserOrder findById(Long orderId);
}
