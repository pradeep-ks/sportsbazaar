package com.sportsbazaar.web.jsp.service;

import org.springframework.mail.SimpleMailMessage;

import com.sportsbazaar.web.jsp.dto.FeedbackDTO;

public interface EmailService {

	void sendFeedback(FeedbackDTO feedbackDTO);
	
	void sendGenericMail(SimpleMailMessage message);
	
}
