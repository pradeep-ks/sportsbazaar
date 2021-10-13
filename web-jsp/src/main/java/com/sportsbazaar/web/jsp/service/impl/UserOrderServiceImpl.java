package com.sportsbazaar.web.jsp.service.impl;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sportsbazaar.persistence.model.User;
import com.sportsbazaar.persistence.model.UserOrder;
import com.sportsbazaar.persistence.repository.UserOrderRepository;
import com.sportsbazaar.persistence.repository.UserRepository;
import com.sportsbazaar.web.jsp.service.UserOrderService;

@Service("userOrderService")
@Transactional(readOnly = true)
public class UserOrderServiceImpl implements UserOrderService {

    @Autowired
    private UserOrderRepository userOrderRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserOrder save(UserOrder order) {
	order.setOrderDate(LocalDateTime.now(Clock.systemUTC()));
	return this.userOrderRepository.saveAndFlush(order);
    }

    @Override
    public List<UserOrder> findAllOrdersByUserId(Long userId) {
	User user = this.userRepository.findById(userId)
		.orElseThrow(() -> new RuntimeException("User with id not found!"));

	return this.userOrderRepository.findByUser(user);
    }

    @Override
    public UserOrder findById(Long orderId) {
	return this.userOrderRepository.findById(orderId)
		.orElseThrow(() -> new RuntimeException("User order with id not found!"));
    }

}
