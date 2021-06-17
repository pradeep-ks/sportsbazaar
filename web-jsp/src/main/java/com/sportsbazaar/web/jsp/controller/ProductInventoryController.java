package com.sportsbazaar.web.jsp.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sportsbazaar.persistence.model.Category;
import com.sportsbazaar.persistence.model.Product;
import com.sportsbazaar.persistence.repository.ProductRepository;
import com.sportsbazaar.web.jsp.dto.ProductDTO;
import com.sportsbazaar.web.jsp.dto.ResponseMessage;
import com.sportsbazaar.web.jsp.service.CategoryService;

@Controller
@RequestMapping("/admin/inventory/products")
public class ProductInventoryController {

	private final Path rootLocation = Paths.get(System.getProperty("user.home"), "Pictures", "uploads", "products");

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryService categoryService;

	@RequestMapping
	public String products(Model model) {
		model.addAttribute("productsList", this.productRepository.findAll());
		model.addAttribute("productsActive", true);
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

		Category cat = this.categoryService.findByCategoryName(productDTO.getCategory());
		p.setCategory(cat);
		p = this.productRepository.save(p);

		// Image upload
		var imgFile = productDTO.getImage();
		Files.createDirectories(rootLocation);
		try {
			if (imgFile.isEmpty()) {
				System.err.println("Cannot store empty image!");
			}
			Path destFile = this.rootLocation.resolve(Paths.get(p.getId() + ".jpg")).normalize().toAbsolutePath();
			try (var inputStream = imgFile.getInputStream()) {
				Files.copy(inputStream, destFile, StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		redirectAttributes.addFlashAttribute("saveSuccess", "Product " + action + "!");
		return "redirect:/admin/inventory/products";
	}

	@RequestMapping(value = "/modify")
	public String modifyProduct(@RequestParam("productId") long productId, Model model,
			RedirectAttributes redirectAttributes) {
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
			model.addAttribute("categories", this.categoryService.findAllCategoryNames());
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
