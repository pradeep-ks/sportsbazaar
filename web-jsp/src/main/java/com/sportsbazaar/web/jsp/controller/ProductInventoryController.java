package com.sportsbazaar.web.jsp.controller;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sportsbazaar.persistence.model.Product;
import com.sportsbazaar.persistence.repository.CategoryRepository;
import com.sportsbazaar.persistence.repository.ProductRepository;
import com.sportsbazaar.web.jsp.dto.ProductDTO;

@Controller
@RequestMapping("/admin/inventory/products")
public class ProductInventoryController {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@RequestMapping
	public String products(Model model) {
		model.addAttribute("productsList", this.productRepository.findAll());
		model.addAttribute("productsActive", true);
		return "admin/productInventory";
	}
	
	@RequestMapping("/addProduct")
	public String addProductForm(Model model) {
		model.addAttribute("categories", this.categoryRepository.findAll());
		model.addAttribute("conditions", Arrays.<String>asList("New", "Used"));
		model.addAttribute("product", new ProductDTO());
		return "admin/productForm";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("product") ProductDTO product, RedirectAttributes redirectAttributes) {
		var newProduct = new Product();
		newProduct.setProductName(product.getProductName());
		newProduct.setManufacturer(product.getManufacturer());
		newProduct.setDescription(product.getDescription());
		newProduct.setPrice(new BigDecimal(product.getPrice()));
		newProduct.setUnitsInStock(product.getUnitsInStock());
		newProduct.setCondition(product.getCondition());
		var value = this.categoryRepository.findByCategoryName(product.getCategory());
		if (value.isPresent()) {
			newProduct.setCategory(value.get());
		} else {
			redirectAttributes.addFlashAttribute("saveError", "Product category not found!");
			return "redirect:/admin/inventory/products";
		}
		this.productRepository.saveAndFlush(newProduct);
		redirectAttributes.addFlashAttribute("saveSuccess", "New product added to inventory!");
		return "redirect:/admin/inventory/products";
	}
}
