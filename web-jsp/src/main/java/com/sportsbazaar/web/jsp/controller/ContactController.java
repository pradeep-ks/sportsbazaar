package com.sportsbazaar.web.jsp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sportsbazaar.web.jsp.dto.FeedbackDTO;

@Controller
public class ContactController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ContactController.class);
	
	@RequestMapping(value = "/contact")
	public String contact(Model model) {
		var feedback = new FeedbackDTO();
		model.addAttribute("feedback", feedback);
		return "contact";
	}
	
	@RequestMapping(value = "/sendFeedback", method = RequestMethod.POST)
	public String sendFeedback(@ModelAttribute("feedback") FeedbackDTO feedbackDTO) {
		LOGGER.debug("Sending feedback from {}", feedbackDTO.getEmail());
		LOGGER.info(feedbackDTO.toString());
		LOGGER.info("Email sent.");
		return "redirect:/contact";
	}
}
