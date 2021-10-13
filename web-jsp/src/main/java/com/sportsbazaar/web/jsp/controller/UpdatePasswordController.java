package com.sportsbazaar.web.jsp.controller;

import java.time.Clock;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sportsbazaar.persistence.model.PasswordResetToken;
import com.sportsbazaar.web.jsp.domain.UserPrincipal;
import com.sportsbazaar.web.jsp.service.PasswordResetTokenService;
import com.sportsbazaar.web.jsp.service.UserService;

@Controller
public class UpdatePasswordController {

    public static final String UPDATE_PASSWORD_URL_MAPPING = "/updatePassword";
    
    public static final String UPDATE_PASSWORD_JSP = "updatePassword";
    
    public static final String PASSWORD_RESET_ATTRIBUTE_NAME = "passwordReset";
    
    public static final String MESSAGE_ATTRIBUTE_NAME = "message";
    
    @Autowired
    private PasswordResetTokenService passwordResetTokenService;
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(value = UPDATE_PASSWORD_URL_MAPPING, method = RequestMethod.GET)
    public String updatePassword(@RequestParam("userId") long userId, @RequestParam("token") String token, Model model) {
	if (userId == 0L || !StringUtils.hasLength(token)) {
	    model.addAttribute(PASSWORD_RESET_ATTRIBUTE_NAME, false);
	    model.addAttribute(MESSAGE_ATTRIBUTE_NAME, "Invalid user id or token");
	    return UPDATE_PASSWORD_JSP;
	}
	
	PasswordResetToken resetToken = this.passwordResetTokenService.findByToken(token);
	if (resetToken == null) {
	    model.addAttribute(PASSWORD_RESET_ATTRIBUTE_NAME, false);
	    model.addAttribute(MESSAGE_ATTRIBUTE_NAME, "Invalid token");
	    return UPDATE_PASSWORD_JSP;
	}
	
	if (resetToken.getUser().getId() != userId) {
	    model.addAttribute(PASSWORD_RESET_ATTRIBUTE_NAME, false);
	    model.addAttribute(MESSAGE_ATTRIBUTE_NAME, "The user id {" + userId + "} is not associated with token = " + token);
	    return UPDATE_PASSWORD_JSP;
	}
	
	if (LocalDateTime.now(Clock.systemUTC()).isAfter(resetToken.getTokenExpiry())) {
	    model.addAttribute(PASSWORD_RESET_ATTRIBUTE_NAME, false);
	    model.addAttribute(MESSAGE_ATTRIBUTE_NAME, "Your password reset token has expired. Please try again!");
	    return UPDATE_PASSWORD_JSP;
	}
	
	model.addAttribute(PASSWORD_RESET_ATTRIBUTE_NAME, true);
	model.addAttribute("principalUserId", userId);
	// Auto-authenticate user as the user is valid
	UserPrincipal userPrincipal = UserPrincipal.mapUserToUserDetails(resetToken.getUser());
	Authentication authentication = new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
	SecurityContextHolder.getContext().setAuthentication(authentication);
	return UPDATE_PASSWORD_JSP;
    }
    
    @RequestMapping(value = UPDATE_PASSWORD_URL_MAPPING, method = RequestMethod.POST)
    public String updatePasswordPost(@RequestParam("principalUserId") long id, @RequestParam("password") String password, Model model) {
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	if (authentication == null) {
	    model.addAttribute(PASSWORD_RESET_ATTRIBUTE_NAME, false);
	    model.addAttribute(MESSAGE_ATTRIBUTE_NAME, "You're not authorized to perform this action!");
	    return UPDATE_PASSWORD_JSP;
	}
	
	UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
	if (userPrincipal.getId() != id) {
	    model.addAttribute(PASSWORD_RESET_ATTRIBUTE_NAME, false);
	    model.addAttribute(MESSAGE_ATTRIBUTE_NAME, "You're not authorized to perform this action!");
	    return UPDATE_PASSWORD_JSP;
	}
	
	this.userService.updatePassword(id, password);
	model.addAttribute(PASSWORD_RESET_ATTRIBUTE_NAME, true);
	return "redirect:/";
    }
}
