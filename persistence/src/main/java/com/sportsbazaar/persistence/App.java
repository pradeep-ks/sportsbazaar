package com.sportsbazaar.persistence;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sportsbazaar.persistence.configuration.AppContextConfig;

public class App {
	public static void main(String[] args) {
		var context = new AnnotationConfigApplicationContext();
		context.getEnvironment().setActiveProfiles("prod");
		context.register(AppContextConfig.class);
		context.refresh();
		
		((ConfigurableApplicationContext) context).close();
	}
}
