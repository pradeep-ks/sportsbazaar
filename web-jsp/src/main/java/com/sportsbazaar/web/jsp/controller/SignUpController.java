package com.sportsbazaar.web.jsp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sportsbazaar.persistence.model.User;
import com.sportsbazaar.web.jsp.domain.UserPrincipal;
import com.sportsbazaar.web.jsp.dto.SignUpDTO;
import com.sportsbazaar.web.jsp.service.UserService;

@Controller
public class SignUpController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String signUp(Model model) {
	model.addAttribute("signUpDTO", new SignUpDTO());
	return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String signUp(@Valid @ModelAttribute("signUpDTO") SignUpDTO payload, BindingResult result, Model model) {
	if (result.hasErrors()) {
	    return "register";
	}
	boolean hasConflict = false;
	if (this.userService.existsByUsername(payload.getUsername())) {
	    hasConflict = true;
	    model.addAttribute("usernameExists", "Username already exists!");
	}
	if (this.userService.existsByEmail(payload.getEmail())) {
	    model.addAttribute("emailExists", "Email already registered!");
	    hasConflict = true;
	}

	User newUser = this.userService.save(payload);
	// Auto-login upon successful registration
	UserPrincipal userPrincipal = UserPrincipal.mapUserToUserDetails(newUser);
	Authentication authentication = new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
	SecurityContextHolder.getContext().setAuthentication(authentication);
	return (hasConflict ? "register" : "redirect:/");
    }
}
