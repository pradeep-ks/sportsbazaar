package com.sportsbazaar.web.jsp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sportsbazaar.persistence.model.Category;
import com.sportsbazaar.persistence.repository.CategoryRepository;
import com.sportsbazaar.web.jsp.exception.ResourceNotFoundException;
import com.sportsbazaar.web.jsp.service.CategoryService;

@Service
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<String> findAllCategoryNames() {
	return this.categoryRepository.findAllCategoryNames();
    }

    @Override
    public Long count() {
	return this.categoryRepository.count();
    }

    @Override
    public List<Category> findAll() {
	return this.categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
	return this.categoryRepository.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException(Category.class.getSimpleName(), "id", id));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
	Category cat = findById(id);
	this.categoryRepository.delete(cat);
    }

    @Override
    @Transactional
    public Category save(Category category) {
	Optional<Category> value = this.categoryRepository.findByCategoryName(category.getCategoryName());
	if (value.isPresent()) {
	    Category cat = value.get();
	    cat.setCategoryName(category.getCategoryName());
	    cat.setDescription(category.getDescription());
	    return this.categoryRepository.saveAndFlush(cat);
	} else {
	    return this.categoryRepository.save(category);
	}
    }

    @Override
    public Category findByCategoryName(String categoryName) {
	Category category = this.categoryRepository.findByCategoryName(categoryName).orElseThrow(
		() -> new ResourceNotFoundException(Category.class.getSimpleName(), "categoryName", categoryName));
	return category;
    }

    @Override
    public Page<Category> findPaginated(int page, int size) {
	Pageable pageable = PageRequest.of(page - 1, size);
	return this.categoryRepository.findAll(pageable);
    }

}
