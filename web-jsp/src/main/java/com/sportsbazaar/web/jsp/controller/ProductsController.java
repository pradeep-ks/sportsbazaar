package com.sportsbazaar.web.jsp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sportsbazaar.persistence.model.Product;
import com.sportsbazaar.web.jsp.service.ProductService;

@Controller
public class ProductsController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping("/products")
	public String productsList(Model model) {
		model.addAttribute("productsList", this.productService.findAll());
		return "products";
	}
	
	@RequestMapping("/productDetails")
	public String productDetails(@RequestParam("id") Long id, Model model) {
		Product p = this.productService.findById(id);
		if (p != null) {
			model.addAttribute("selectedProduct", p);
			return "productDetails";
		} else {
			model.addAttribute("error", "Product with id = " + id + " not found!");
			return "redirect:/products";
		}
	}
}
