package com.sportsbazaar.web.jsp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import com.sportsbazaar.web.jsp.service.EmailService;
import com.sportsbazaar.web.jsp.service.impl.MockEmailService;

@Configuration
@Profile("dev")
@PropertySource("file:///${user.home}/Documents/.sportsbazaar/application-dev.properties")
public class WebMvcDevConfig {

    @Bean
    public EmailService emailService() {
	return new MockEmailService();
    }
}
