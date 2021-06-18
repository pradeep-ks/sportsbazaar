package com.sportsbazaar.web.jsp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
	public String productsList(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
			@RequestParam(name = "size", required = false, defaultValue = "5") int size, Model model) {
		Page<Product> paged = this.productService.findPaginated(page, size);
		model.addAttribute("productsList", paged.getContent());
		// For pagination
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", paged.getTotalPages());
		model.addAttribute("totalElements", paged.getTotalElements());
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
