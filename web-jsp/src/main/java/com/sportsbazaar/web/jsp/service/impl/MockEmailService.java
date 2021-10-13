package com.sportsbazaar.web.jsp.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

import com.sportsbazaar.web.jsp.service.AbstractEmailService;

public class MockEmailService extends AbstractEmailService {

	private static final Logger log = LoggerFactory.getLogger(MockEmailService.class);
	
	@Override
	public void sendGenericMail(SimpleMailMessage message) {
		log.debug("Sending email to: {}", message.getTo()[0]);
		log.info(message.toString());
		log.info("Email sent.");
	}

}
