package com.sportsbazaar.web.jsp.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sportsbazaar.persistence.model.Category;
import com.sportsbazaar.persistence.repository.CategoryRepository;
import com.sportsbazaar.web.jsp.dto.ResponseMessage;

@Controller
@RequestMapping("/admin/inventory/category")
public class CategoryInventoryController {

	private static final Logger log = LoggerFactory.getLogger(CategoryInventoryController.class);

	private static final String REDIRECT_URL = "/admin/inventory/category";

	@Autowired
	private CategoryRepository categoryRepository;

	@RequestMapping
	public String categories(Model model) {
		model.addAttribute("categoryList", this.categoryRepository.findAll());
		model.addAttribute("categoryActive", true);
		return "admin/categoryInventory";
	}

	@RequestMapping("/addCategory")
	public String addCategory(Model model) {
		model.addAttribute("category", new Category());
		return "admin/categoryForm";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String createCategory(@Valid @ModelAttribute Category category, BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "admin/categoryForm";
		}
		
		var msg = "";
		var value = this.categoryRepository.findByCategoryName(category.getCategoryName());
		if (value.isEmpty()) {
			this.categoryRepository.saveAndFlush(category);
			msg = "New category created successfully!";
		} else {
			var cat = value.get();
			cat.setDescription(category.getDescription());
			this.categoryRepository.saveAndFlush(cat);
			msg = "Category updated successfully!";
		}
		ResponseMessage responseMsg = new ResponseMessage(msg, true);
		redirectAttributes.addFlashAttribute("message", responseMsg);
		return "redirect:/admin/inventory/category";
	}

	@RequestMapping(value = "/modify/{id}")
	public String modifyCategory(@PathVariable("id") long id, Model model, RedirectAttributes redirectAttributes) {
		var value = this.categoryRepository.findById(id);
		if (value.isPresent()) {
			var category = value.get();
			model.addAttribute("category", category);
			return "admin/categoryForm";
		} else {
			redirectAttributes.addFlashAttribute("message",
					new ResponseMessage("Category with id : " + id + " does not exists!", false));
			return "redirect:" + REDIRECT_URL;
		}
	}

	@RequestMapping("/delete")
	public String deleteCategory(@RequestParam("id") long id, RedirectAttributes redirectAttributes) {
		log.debug("Deleting category with id: {}", id);
		this.categoryRepository.deleteById(id);
		var message = new ResponseMessage();
		message.setMessage(String.format("Category deleted successfully!", id));
		message.setSuccess(true);
		redirectAttributes.addFlashAttribute("message", message);
		log.info("Category with id: {}, deleted", id);
		return "redirect:" + REDIRECT_URL;
	}
}
