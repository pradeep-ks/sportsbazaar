package com.sportsbazaar.web.jsp.controller;

import java.io.IOException;
import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sportsbazaar.persistence.model.Product;
import com.sportsbazaar.web.jsp.dto.ProductDTO;
import com.sportsbazaar.web.jsp.dto.ResponseMessage;
import com.sportsbazaar.web.jsp.service.CategoryService;
import com.sportsbazaar.web.jsp.service.ProductService;
import com.sportsbazaar.web.jsp.util.ProductUtil;

@Controller
@RequestMapping("/admin/inventory/products")
public class ProductInventoryController {

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@RequestMapping
	public String products(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
			@RequestParam(name = "size", required = false, defaultValue = "5") int size, Model model) {
		Page<Product> paged = this.productService.findPaginated(page, size);
		model.addAttribute("productsList", paged.getContent());
		model.addAttribute("productsActive", true);
		// For pagination
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", paged.getTotalPages());
		model.addAttribute("totalElements", paged.getTotalElements());
		return "admin/productInventory";
	}

	@RequestMapping("/addProduct")
	public String addProductForm(Model model) {
		model.addAttribute("categories", this.categoryService.findAllCategoryNames());
		model.addAttribute("conditions", Arrays.<String>asList("New", "Used"));
		model.addAttribute("product", new ProductDTO());
		return "admin/productForm";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@Valid @ModelAttribute("product") ProductDTO productDTO, BindingResult result,
			RedirectAttributes redirectAttributes) throws IOException {
		if (result.hasErrors()) {
			return "admin/productForm";
		}

		this.productService.save(productDTO);
		redirectAttributes.addFlashAttribute("saveSuccess", "Product saved!");
		return "redirect:/admin/inventory/products";
	}

	@RequestMapping(value = "/modify")
	public String modifyProduct(@RequestParam("productId") long productId, Model model, RedirectAttributes attrs) {
		var value = this.productService.findById(productId);
		if (value != null) {
			model.addAttribute("product", ProductUtil.entityToDTO(value));
			model.addAttribute("categories", this.categoryService.findAllCategoryNames());
			model.addAttribute("conditions", Arrays.<String>asList("New", "Used"));
			return "admin/productForm";
		}
		attrs.addFlashAttribute("error", "Product not found with id = " + productId);
		return "redirect:/admin/inventory/products";
	}

	@RequestMapping("/delete")
	public String deleteProduct(@RequestParam("productId") long productId, RedirectAttributes attributes) {
		this.productService.deleteById(productId);
		var message = new ResponseMessage();
		message.setMessage(String.format("Product with id = %d, deleted successfully!", productId));
		message.setSuccess(true);
		attributes.addFlashAttribute("message", message);
		return "redirect:/admin/inventory/products";
	}
}
