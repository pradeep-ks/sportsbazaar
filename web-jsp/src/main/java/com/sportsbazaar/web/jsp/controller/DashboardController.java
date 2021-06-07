package com.sportsbazaar.web.jsp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sportsbazaar.persistence.repository.CategoryRepository;
import com.sportsbazaar.persistence.repository.ProductRepository;
import com.sportsbazaar.web.jsp.dto.CategorySummary;
import com.sportsbazaar.web.jsp.dto.ProductSummary;

@Controller
@RequestMapping("/admin/dashboard")
public class DashboardController {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@RequestMapping
	public String dashboard(Model model) {
		var productSummary = new ProductSummary();
		var categorySummary = new CategorySummary();
		
		var totalProducts = this.productRepository.count();
		var productsInStock = this.productRepository.countByUnitsInStockGreaterThan(0);
		var productsOutOfStock = totalProducts - productsInStock;
		productSummary.setProductsInStock(productsInStock);
		productSummary.setProductsOutOfStock(productsOutOfStock);
		productSummary.setTotalProducts(totalProducts);
		
		var totalCategories = this.categoryRepository.count();
		categorySummary.setTotalCategories(totalCategories);
		
		model.addAttribute("productSummary", productSummary);
		model.addAttribute("categorySummary", categorySummary);
		model.addAttribute("dashboardActive", true);
		return "admin/dashboard";
	}
}
