package com.sportsbazaar.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sportsbazaar.persistence.model.Category;
import com.sportsbazaar.persistence.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByProductName(String productName);
	
	List<Product> findByManufacturer(String manufacturer);
	
	List<Product> findByCategory(Category category);
	
}
