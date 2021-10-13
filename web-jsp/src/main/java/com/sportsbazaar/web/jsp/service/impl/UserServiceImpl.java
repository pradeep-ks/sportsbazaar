package com.sportsbazaar.web.jsp.service.impl;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sportsbazaar.persistence.enums.RoleName;
import com.sportsbazaar.persistence.model.Cart;
import com.sportsbazaar.persistence.model.Role;
import com.sportsbazaar.persistence.model.ShippingAddress;
import com.sportsbazaar.persistence.model.User;
import com.sportsbazaar.persistence.repository.RoleRepository;
import com.sportsbazaar.persistence.repository.UserRepository;
import com.sportsbazaar.web.jsp.dto.SignUpDTO;
import com.sportsbazaar.web.jsp.service.UserService;

@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User save(SignUpDTO payload) {
	User user = new User();
	user.setUsername(payload.getUsername());
	user.setEmail(payload.getEmail());
	user.setPassword(this.passwordEncoder.encode(payload.getPassword()));
	user.setEnabled(true);
	Role role = this.roleRepository.findByRoleName(RoleName.ROLE_CUSTOMER)
		.orElseThrow(() -> new RuntimeException("Could not set user role!"));
	user.setRoles(Collections.singleton(role));

	Cart cart = new Cart();
	cart.setUser(user);
	user.setCart(cart);
	
	ShippingAddress address = new ShippingAddress();
	user.setShippingAddress(address);
	address.setUser(user);
	
	return this.userRepository.saveAndFlush(user);
    }

    @Override
    public boolean existsByUsername(String username) {
	return this.userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
	return this.userRepository.existsByEmail(email);
    }

    @Override
    @Transactional
    public void updatePassword(long userId, String password) {
	password = this.passwordEncoder.encode(password);
	this.userRepository.updatePassword(userId, password);
    }

    @Override
    public User findByUsername(String name) {
	User user = this.userRepository.findByUsername(name)
		.orElseThrow(() -> new RuntimeException("User not found with username = " + name));
	return user;
    }

    @Override
    public User update(User user) {
	return this.userRepository.saveAndFlush(user);
    }

}
