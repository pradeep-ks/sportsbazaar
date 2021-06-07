package com.sportsbazaar.web.jsp.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sportsbazaar.persistence.model.Category;
import com.sportsbazaar.persistence.model.Product;
import com.sportsbazaar.persistence.repository.CategoryRepository;
import com.sportsbazaar.persistence.repository.ProductRepository;
import com.sportsbazaar.web.jsp.dto.ProductDTO;

@Controller
@RequestMapping("/admin")
public class InventoryController {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@RequestMapping("/inventory")
	public String inventory(@RequestParam(name = "activeTab", required = false) String activeTab, Model model) {
		var categories = this.categoryRepository.findAll();
		var products = this.productRepository.findAll();
		
		var category = new Category();
		var productDTO = new ProductDTO();
		model.addAttribute("categoryDto", category);
		model.addAttribute("productDTO", productDTO);
		model.addAttribute("categories", categories);
		model.addAttribute("products", products);
		
		if (activeTab == null) {
			activeTab = "products";
		}
		
		if (activeTab.equals("category")) {
			model.addAttribute("categoryTabSelected", true);
		} else {
			model.addAttribute("productsTabSelected", true);
		}
		
		return "inventory";
	}
	
	@RequestMapping(value = "/inventory/category", method = RequestMethod.POST)
	public String createCategory(@ModelAttribute("categoryDto") Category category, RedirectAttributes redirectAttributes) {
		this.categoryRepository.save(category);
		redirectAttributes.addFlashAttribute("success", true);
		return "redirect:/admin/inventory?activeTab=category";
	}
	
	@RequestMapping(value = "/inventory/product", method = RequestMethod.POST)
	public String addProduct(@ModelAttribute("productDTO") ProductDTO productDTO) {
		var product = new Product();
		product.setId(productDTO.getId());
		product.setProductName(productDTO.getProductName());
		product.setManufacturer(productDTO.getManufacturer());
		product.setDescription(productDTO.getDescription());
		product.setCondition(productDTO.getCondition());
		product.setUnitsInStock(productDTO.getUnitsInStock());
		
		product.setPrice(new BigDecimal(productDTO.getPrice()));
		var category = this.categoryRepository.findByCategoryName(productDTO.getCategory()).get();
		product.setCategory(category);
		
		this.productRepository.save(product);
		
		return "redirect:/admin/inventory?activeTab=products"; 
	}
}
