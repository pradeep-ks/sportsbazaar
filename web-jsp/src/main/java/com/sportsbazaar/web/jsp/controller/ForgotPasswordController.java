package com.sportsbazaar.web.jsp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ForgotPasswordController {

    @RequestMapping(value = "/forgotPassword")
    public String forgotPassword() {
	return "forgotPassword";
    }
}
