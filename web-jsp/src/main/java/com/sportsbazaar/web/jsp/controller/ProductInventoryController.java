package com.sportsbazaar.web.jsp.controller;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sportsbazaar.persistence.model.Product;
import com.sportsbazaar.persistence.repository.CategoryRepository;
import com.sportsbazaar.persistence.repository.ProductRepository;
import com.sportsbazaar.web.jsp.dto.ProductDTO;
import com.sportsbazaar.web.jsp.dto.ResponseMessage;

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
		model.addAttribute("categories", this.categoryRepository.findAllCategoryNames());
		model.addAttribute("conditions", Arrays.<String>asList("New", "Used"));
		model.addAttribute("product", new ProductDTO());
		return "admin/productForm";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") ProductDTO productDTO, RedirectAttributes redirectAttributes) {
		var action = "";
		var value = this.productRepository.findById(productDTO.getId());
		Product p;
		if (value.isPresent()) {
			p = value.get();
			p.setId(productDTO.getId());
			action = "updated";
		} else {
			p = new Product();
			action = "created";
		}
		p.setProductName(productDTO.getProductName());
		p.setManufacturer(productDTO.getManufacturer());
		p.setDescription(productDTO.getDescription());
		p.setPrice(new BigDecimal(productDTO.getPrice()));
		p.setUnitsInStock(productDTO.getUnitsInStock());
		p.setCondition(productDTO.getCondition());
		
		var cat = this.categoryRepository.findByCategoryName(productDTO.getCategory());
		if (cat.isPresent()) {
			p.setCategory(cat.get());
		} else {
			redirectAttributes.addFlashAttribute("saveError", "Product category not found!");
			return "redirect:/admin/inventory/products";
		}
		this.productRepository.save(p);
		redirectAttributes.addFlashAttribute("saveSuccess", "Product " + action + "!");
		return "redirect:/admin/inventory/products";
	}
	
	@RequestMapping(value = "/modify")
	public String modifyProduct(@RequestParam("productId") long productId, Model model, RedirectAttributes redirectAttributes) {
		var value = this.productRepository.findById(productId);
		if (value.isPresent()) {
			var productDTO = new ProductDTO();
			var product = value.get();
			productDTO.setId(product.getId());
			productDTO.setProductName(product.getProductName());
			productDTO.setManufacturer(product.getManufacturer());
			productDTO.setDescription(product.getDescription());
			productDTO.setCondition(product.getCondition());
			productDTO.setCategory(product.getCategory().getCategoryName());
			productDTO.setPrice(product.getPrice().doubleValue());
			productDTO.setUnitsInStock(product.getUnitsInStock());
			
			model.addAttribute("product", productDTO);
			model.addAttribute("categories", this.categoryRepository.findAllCategoryNames());
			model.addAttribute("conditions", Arrays.<String>asList("New", "Used"));
			return "admin/productForm";
		} else {
			redirectAttributes.addFlashAttribute("message",
					new ResponseMessage("Product with id : " + productId + " does not exists!", false));
			return "redirect:/admin/inventory/products";
		}
	}
	
	@RequestMapping("/delete")
	public String deleteProduct(@RequestParam("productId") long productId, RedirectAttributes attributes) {
		this.productRepository.deleteById(productId);
		var message = new ResponseMessage();
		message.setMessage(String.format("Product with id = %d, deleted successfully!", productId));
		message.setSuccess(true);
		attributes.addFlashAttribute("message", message);
		return "redirect:/admin/inventory/products";
	}
}
