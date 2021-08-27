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
		// following code is for testing purpose only....
		
//		var categoryRepository = context.getBean(CategoryRepository.class);
//		var category = new Category();
//		category.setCategoryName("Basketball");
//		categoryRepository.saveAndFlush(category);
//		
//		category = new Category();
//		category.setCategoryName("Cricket");
//		categoryRepository.saveAndFlush(category);
//		
//		category = new Category();
//		category.setCategoryName("Hockey");
//		categoryRepository.saveAndFlush(category);
//		
//		category = new Category();
//		category.setCategoryName("Vollyball");
//		categoryRepository.saveAndFlush(category);
		
		
		((ConfigurableApplicationContext) context).close();
	}
}
