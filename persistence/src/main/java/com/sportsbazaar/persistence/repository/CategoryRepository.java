package com.sportsbazaar.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sportsbazaar.persistence.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	// select c from category c where c.categoryName=?
	Optional<Category> findByCategoryName(String categoryName);

	// select c from category c where c.categoryName in ?
	List<Category> findByCategoryNameIn(List<String> categoryNames);

}
