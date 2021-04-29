package com.sportsbazaar.web.jsp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

	private static final Logger log = LoggerFactory.getLogger(AppController.class);

	@RequestMapping("/")
	public String index() {
		log.info("returing 'index'");
		return "index";
	}
	
}
