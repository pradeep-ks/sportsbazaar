package com.sportsbazaar.web.jsp.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sportsbazaar.persistence.model.Category;

public interface CategoryService {

	List<String> findAllCategoryNames();
	
	List<Category> findAll();
	
	Category findById(Long id);
	
	void deleteById(Long id);
	
	Category save(Category category);
	
	Long count();

	Category findByCategoryName(String category);
	
	Page<Category> findPaginated(int page, int size);
	
}
