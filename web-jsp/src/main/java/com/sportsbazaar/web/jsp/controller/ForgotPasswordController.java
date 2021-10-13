package com.sportsbazaar.web.jsp.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sportsbazaar.web.jsp.service.EmailService;
import com.sportsbazaar.web.jsp.service.PasswordResetTokenService;

@Controller
public class ForgotPasswordController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ForgotPasswordController.class);
    
    public static final String FORGOT_PASSWORD_URL_MAPPING = "/forgotPassword";
    
    public static final String FORGOT_PASSWORD_JSP = "forgotPassword";
    
    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordResetTokenService passwordResetTokenService;

    @Value("${app.default.from.address}")
    private String from;

    @RequestMapping(value = FORGOT_PASSWORD_URL_MAPPING, method = RequestMethod.GET)
    public String forgotPassword() {
	return FORGOT_PASSWORD_JSP;
    }

    @RequestMapping(value = FORGOT_PASSWORD_URL_MAPPING, method = RequestMethod.POST)
    public String forgotPassword(HttpServletRequest request, @RequestParam("email") String email, Model model) {
	var resetToken = this.passwordResetTokenService.generatePasswordResetTokenFromEmail(email);
	if (resetToken == null) {
	    LOGGER.error("Could not send password reset email as the email is not registered");
	} else {
	    var user = resetToken.getUser();
	    var token = resetToken.getToken();
	    var passwordResetUrl = generatePasswordResetToken(request, user.getId(), token);
	    LOGGER.info("Password reset url = " + passwordResetUrl);
	    var mailMessage = new SimpleMailMessage();
	    mailMessage.setTo(email);
	    mailMessage.setFrom(from);
	    mailMessage.setSubject("[Sports Bazaar] Password Reset Link");
	    mailMessage.setText("Click on the link below to reset your password: -\r\n" + passwordResetUrl);
	    
	    this.emailService.sendGenericMail(mailMessage);
	}
	model.addAttribute("emailSent", true);
	return FORGOT_PASSWORD_JSP;
    }

    private String generatePasswordResetToken(HttpServletRequest request, Long id, String token) {
	// http://localhost:8080/web-jsp/updatePassword?userId=<>&token=<>
	var builder = new StringBuilder();
	builder.append(request.getScheme()).append("://").append(request.getServerName()).append(":")
		.append(request.getServerPort()).append(request.getContextPath()).append("/updatePassword")
		.append("?userId=").append(id).append("&token=").append(token);
	return builder.toString();
    }
}
