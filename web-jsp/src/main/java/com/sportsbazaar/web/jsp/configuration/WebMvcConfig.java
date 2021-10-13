package com.sportsbazaar.web.jsp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.sportsbazaar")
public class WebMvcConfig implements WebMvcConfigurer {

    public static final String IMG_LOCATION = System.getProperty("user.home") + "/Pictures/uploads/products/";

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
	registry.jsp("/WEB-INF/views/", ".jsp");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	registry.addResourceHandler("/uploads/products/**").addResourceLocations("file:///" + IMG_LOCATION);
	registry.addResourceHandler("/resources/**", "/webjars/**").addResourceLocations("/WEB-INF/resources/",
		"classpath:META-INF/resources/webjars/");
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
	var messageSource = new ReloadableResourceBundleMessageSource();
	messageSource.setBasename("classpath:i18n/messages");
	messageSource.setDefaultEncoding("utf-8");
	messageSource.setUseCodeAsDefaultMessage(true);
	return messageSource;
    }

    @Bean
    public LocaleResolver localeResolver() {
	var localeResolver = new SessionLocaleResolver();
	return localeResolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
	var localeChangeInterceptor = new LocaleChangeInterceptor();
	localeChangeInterceptor.setParamName("lang");
	registry.addInterceptor(localeChangeInterceptor);
    }

    @Bean(name = "multipartResolver")
    public StandardServletMultipartResolver multipartResolver() {
	var multipartResolver = new StandardServletMultipartResolver();
	return multipartResolver;
    }

}
