package com.sportsbazaar.web.jsp.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

public class ProductDTO {

	private long id;
	
	@NotBlank(message = "Name of product is required")
	private String productName;
	
	@NotBlank(message = "Name of manufacturer is required")
	private String manufacturer;
	
	@NotBlank(message = "Product description is required")
	private String description;
	
	@NotBlank(message = "Category is required")
	private String category;
	
	@NotBlank(message = "Condition is required")
	private String condition;
	
	@Min(value = 10, message = "Price must be 10 or above")
	private double price;
	
	@Min(value = 0, message = "Units in stock must be 0 or above")
	private int unitsInStock;
	
	private MultipartFile image;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getUnitsInStock() {
		return unitsInStock;
	}

	public void setUnitsInStock(int unitsInStock) {
		this.unitsInStock = unitsInStock;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

}
