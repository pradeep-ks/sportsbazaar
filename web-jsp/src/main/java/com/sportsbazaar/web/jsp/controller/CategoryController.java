package com.sportsbazaar.web.jsp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.sportsbazaar.web.jsp.service.CategoryService;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getCategories() {
		var categories = this.categoryService.findAllCategoryNames();
		var gson = new Gson();
		return gson.toJson(categories);
	}
}
