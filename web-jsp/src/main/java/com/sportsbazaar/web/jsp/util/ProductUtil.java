package com.sportsbazaar.web.jsp.util;

import java.math.BigDecimal;

import com.sportsbazaar.persistence.model.Category;
import com.sportsbazaar.persistence.model.Product;
import com.sportsbazaar.web.jsp.dto.ProductDTO;

public class ProductUtil {

	public static Product productDTOToProduct(ProductDTO dto, Category category) {
		var product = new Product();
		product.setId(dto.getId());
		product.setProductName(dto.getProductName());
		product.setManufacturer(dto.getManufacturer());
		product.setDescription(dto.getDescription());
		product.setCondition(dto.getCondition());
		product.setUnitsInStock(dto.getUnitsInStock());
		product.setPrice(new BigDecimal(dto.getPrice()));
		product.setImage(dto.getImage());
		product.setCategory(category);
		return product;
	}
}
