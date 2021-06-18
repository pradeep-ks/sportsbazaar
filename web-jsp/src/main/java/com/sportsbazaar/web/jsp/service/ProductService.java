package com.sportsbazaar.web.jsp.service;

import java.util.List;

import com.sportsbazaar.persistence.model.Product;
import com.sportsbazaar.web.jsp.dto.ProductDTO;

public interface ProductService {

	Product save(ProductDTO product);
	
	List<Product> findAll();
	
	Product findById(Long id);
	
	void deleteById(Long id);
	
	Long count();
	
	Long countByUnitsInStockGreaterThan(int value);
	
}
