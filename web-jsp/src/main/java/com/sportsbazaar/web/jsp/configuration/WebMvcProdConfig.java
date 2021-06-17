package com.sportsbazaar.web.jsp.configuration;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.sportsbazaar.web.jsp.service.EmailService;
import com.sportsbazaar.web.jsp.service.impl.SmtpEmailService;

@Configuration
@Profile("prod")
@PropertySource("file:///${user.home}/Documents/.sportsbazaar/application-prod.properties")
public class WebMvcProdConfig {

	@Autowired
	Environment env;
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
	
	@Bean
	public MailSender mailSender() {
		var mailSender = new JavaMailSenderImpl();
		
		mailSender.setHost(env.getProperty("mail.host"));
		mailSender.setUsername(env.getProperty("mail.username"));
		mailSender.setPassword(env.getProperty("mail.password"));
		mailSender.setJavaMailProperties(getJavaMailProperties());
		
		return mailSender;
	}

	private Properties getJavaMailProperties() {
		var properties = new Properties();
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.auth", env.getProperty("mail.smtp.auth"));
		properties.put("mail.smtp.socketFactory.class", env.getProperty("mail.smtp.socketFactory.class"));
		properties.put("mail.smtp.socketFactory.port", env.getProperty("mail.smtp.socketFactory.port"));
		properties.put("mail.smtp.starttls.enable", env.getProperty("mail.smtp.starttls.enable"));
		properties.put("mail.smtp.ssl.enable", env.getProperty("mail.smtp.ssl.enable"));
		
		return properties;
	}
}
