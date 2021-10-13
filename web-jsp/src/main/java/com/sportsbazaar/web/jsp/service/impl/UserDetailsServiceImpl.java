package com.sportsbazaar.web.jsp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sportsbazaar.persistence.model.User;
import com.sportsbazaar.persistence.repository.UserRepository;
import com.sportsbazaar.web.jsp.domain.UserPrincipal;

@Service
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	User user = this.userRepository.findByUsername(username)
		.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

	return UserPrincipal.mapUserToUserDetails(user);
    }

}
