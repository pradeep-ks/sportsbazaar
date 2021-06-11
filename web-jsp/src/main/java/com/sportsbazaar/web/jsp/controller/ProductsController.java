package com.sportsbazaar.web.jsp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sportsbazaar.persistence.model.Product;
import com.sportsbazaar.persistence.repository.ProductRepository;

@Controller
public class ProductsController {

	@Autowired
	private ProductRepository productRepository;
	
	@RequestMapping("/products")
	public String productsList(Model model) {
		model.addAttribute("productsList", this.productRepository.findAll());
		return "products";
	}
	
	@RequestMapping("/productDetails")
	public String productDetails(@RequestParam("id") Long id, Model model) {
		Optional<Product> value = this.productRepository.findById(id);
		if (value.isPresent()) {
			model.addAttribute("selectedProduct", value.get());
			return "productDetails";
		} else {
			model.addAttribute("error", "Product with id = " + id + " not found!");
			return "productDetails";
		}
	}
}
