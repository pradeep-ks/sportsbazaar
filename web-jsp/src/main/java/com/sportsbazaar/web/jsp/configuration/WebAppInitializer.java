package com.sportsbazaar.web.jsp.configuration;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    private static final long MAX_FILE_SIZE = 5_242_880L;	// 5 MB
    private static final long MAX_REQUEST_SIZE = 20_971_520L;	// 20 MB
    private static final int FILE_SIZE_THRESHOLD = 0;		// 

    @Override
    protected void customizeRegistration(Dynamic registration) {
	registration.setMultipartConfig(getMultipartConfigElement());
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
	super.onStartup(servletContext);
	servletContext.setInitParameter("spring.profiles.active", "dev");
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
	return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
	return new Class[] { WebMvcConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
	return new String[] { "/" };
    }

    private MultipartConfigElement getMultipartConfigElement() {
	var config = new MultipartConfigElement(WebMvcConfig.IMG_LOCATION, MAX_FILE_SIZE, MAX_REQUEST_SIZE,
		FILE_SIZE_THRESHOLD);
	return config;
    }
}
