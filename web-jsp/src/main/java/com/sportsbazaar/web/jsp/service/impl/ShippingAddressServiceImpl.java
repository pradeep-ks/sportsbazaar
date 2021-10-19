package com.sportsbazaar.web.jsp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sportsbazaar.persistence.model.ShippingAddress;
import com.sportsbazaar.persistence.model.User;
import com.sportsbazaar.persistence.repository.ShippingAddressRepository;
import com.sportsbazaar.persistence.repository.UserRepository;
import com.sportsbazaar.web.jsp.exception.ResourceNotFoundException;
import com.sportsbazaar.web.jsp.service.ShippingAddressService;

@Service
@Transactional(readOnly = true)
public class ShippingAddressServiceImpl implements ShippingAddressService {

    @Autowired
    private ShippingAddressRepository shippingAddressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void save(ShippingAddress address, Long userId) {
	User user = this.userRepository.findById(userId)
		.orElseThrow(() -> new ResourceNotFoundException(User.class.getSimpleName(), "id", userId));
	address.setUser(user);
	this.shippingAddressRepository.saveAndFlush(address);
    }

}
