package com.sportsbazaar.web.jsp.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sportsbazaar.persistence.model.Category;
import com.sportsbazaar.persistence.model.Product;
import com.sportsbazaar.persistence.repository.ProductRepository;
import com.sportsbazaar.web.jsp.dto.ProductDTO;
import com.sportsbazaar.web.jsp.service.CategoryService;
import com.sportsbazaar.web.jsp.service.ProductService;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

	private final Path rootLocation = Paths.get(System.getProperty("user.home"), "Pictures", "uploads", "products");

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryService categoryService;

	@Override
	@Transactional
	public Product save(ProductDTO productDTO) {
		var value = this.productRepository.findById(productDTO.getId());
		Product p;
		if (value.isPresent()) {
			p = value.get();
			p.setId(productDTO.getId());
		} else {
			p = new Product();
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
		try {
			Files.createDirectories(rootLocation);
		} catch (IOException e1) {
			System.err.println(e1);
		}
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
		return p;
	}

	@Override
	public List<Product> findAll() {
		return this.productRepository.findAll();
	}

	@Override
	public Product findById(Long id) {
		return this.productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found with id = " + id));
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		var product = findById(id);
		this.productRepository.delete(product);
	}

	@Override
	public Long count() {
		return this.productRepository.count();
	}

	@Override
	public Long countByUnitsInStockGreaterThan(int value) {
		return this.productRepository.countByUnitsInStockGreaterThan(value);
	}

	@Override
	public Page<Product> findPaginated(int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		return this.productRepository.findAll(pageable);
	}

}
