package com.sportsbazaar.web.jsp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.sportsbazaar.web.jsp.dto.FeedbackDTO;

public abstract class AbstractEmailService implements EmailService {

	@Value("${app.default.from.address}")
	private String fromAddress;
	
	protected SimpleMailMessage feedbackDTOToSimpleMailMessage(FeedbackDTO feedbackDTO) {
		var message = new SimpleMailMessage();
		
		message.setTo(feedbackDTO.getEmail());
		message.setFrom(fromAddress);
		message.setSubject("Sports Bazaar Acknowledgement");
		message.setText("""
				Thank you for contacting us. Your feedback is valuable to us.
				Our representative will contact you, if necessary, for further assistance.
				""");	// Text block works with Java 15+
		
		return message;
	}

	@Override
	public void sendFeedback(FeedbackDTO feedbackDTO) {
		var message = feedbackDTOToSimpleMailMessage(feedbackDTO);
		sendGenericMail(message);
	}
	
}
