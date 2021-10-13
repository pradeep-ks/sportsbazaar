package com.sportsbazaar.web.jsp.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.sportsbazaar.web.jsp.service.AbstractEmailService;

public class SmtpEmailService extends AbstractEmailService {

	private static final Logger log = LoggerFactory.getLogger(SmtpEmailService.class);
	
	@Autowired
	private MailSender mailSender;
	
	@Override
	public void sendGenericMail(SimpleMailMessage message) {
		mailSender.send(message);
		log.info("Email sent using SMTP");
	}

}
