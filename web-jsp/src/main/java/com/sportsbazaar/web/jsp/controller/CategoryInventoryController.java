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
import com.sportsbazaar.web.jsp.dto.ResponseMessage;
import com.sportsbazaar.web.jsp.service.CategoryService;

@Controller
@RequestMapping("/admin/inventory/category")
public class CategoryInventoryController {

	private static final Logger log = LoggerFactory.getLogger(CategoryInventoryController.class);

	public static final String VIEW_NAME_CATEGORY_INVENTORY = "admin/categoryInventory";
	
	public static final String VIEW_NAME_CATEGORY_FORM = "admin/categoryForm";
	
	private static final String REDIRECT_URL_CATEGORY = "redirect:/admin/inventory/category";

	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping
	public String categories(Model model) {
		model.addAttribute("categoryList", this.categoryService.findAll());
		model.addAttribute("categoryActive", true);
		return VIEW_NAME_CATEGORY_INVENTORY;
	}

	@RequestMapping("/addCategory")
	public String addCategory(Model model) {
		model.addAttribute("category", new Category());
		return VIEW_NAME_CATEGORY_FORM;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String createCategory(@Valid @ModelAttribute Category category, BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return VIEW_NAME_CATEGORY_FORM;
		}
		
		this.categoryService.save(category);
		ResponseMessage responseMsg = new ResponseMessage("Category saved!", true);
		redirectAttributes.addFlashAttribute("message", responseMsg);
		return REDIRECT_URL_CATEGORY;
	}

	@RequestMapping(value = "/modify/{id}")
	public String modifyCategory(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
		Category category = this.categoryService.findById(id);
		model.addAttribute("category", category);
		return REDIRECT_URL_CATEGORY;
	}

	@RequestMapping("/delete")
	public String deleteCategory(@RequestParam("id") long id, RedirectAttributes redirectAttributes) {
		log.debug("Deleting category with id: {}", id);
		this.categoryService.deleteById(id);
		var message = new ResponseMessage();
		message.setMessage(String.format("Category deleted successfully!", id));
		message.setSuccess(true);
		redirectAttributes.addFlashAttribute("message", message);
		log.info("Category with id: {}, deleted", id);
		return REDIRECT_URL_CATEGORY;
	}
}
