package com.sportsbazaar.web.jsp.service.impl;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sportsbazaar.persistence.model.PasswordResetToken;
import com.sportsbazaar.persistence.model.User;
import com.sportsbazaar.persistence.repository.PasswordResetTokenRepository;
import com.sportsbazaar.persistence.repository.UserRepository;
import com.sportsbazaar.web.jsp.service.PasswordResetTokenService;

@Service
@Transactional(readOnly = true)
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public PasswordResetToken findByToken(String token) {
	return this.passwordResetTokenRepository.findByToken(token).orElse(null);
    }

    @Override
    @Transactional
    public PasswordResetToken generatePasswordResetTokenFromEmail(String email) {
	PasswordResetToken resetToken = null;
	Optional<User> optional = this.userRepository.findByEmail(email);
	if (optional.isPresent()) {
	    System.out.println("FOUND User with email = " + email);
	    User user = optional.get();
	    String token = UUID.randomUUID().toString();
	    resetToken = new PasswordResetToken(token, user, LocalDateTime.now(Clock.systemUTC()), 3);
	    resetToken = this.passwordResetTokenRepository.saveAndFlush(resetToken);
	} else {
	    System.err.println("Could not find user with email = " + email);
	}
	return resetToken;
    }

}
