package com.sportsbazaar.web.jsp.controller.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sportsbazaar.web.jsp.exception.AppException;
import com.sportsbazaar.web.jsp.exception.ResourceNotFoundException;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleResourceNotFoundException(Model model, ResourceNotFoundException exception) {
	model.addAttribute("resourceError", exception.getMessage());
	return "errors";
    }
    
    @ExceptionHandler(AppException.class)
    public String handleAppException(Model model, AppException exception) {
	model.addAttribute("error", exception.getMessage());
	return "errors";
    }
}
