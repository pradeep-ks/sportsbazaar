package com.sportsbazaar.web.jsp.dto;

import org.springframework.web.multipart.MultipartFile;

public class ProductDTO {

	private long id;
	
	private String productName;
	
	private String manufacturer;
	
	private String description;
	
	private String category;
	
	private String condition;
	
	private double price;
	
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

<<<<<<< HEAD:web-jsp/src/main/java/com/sportsbazaar/web/jsp/payload/ProductDTO.java
	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
=======
	@Override
	public String toString() {
		return "ProductDTO [id=" + id + ", productName=" + productName + ", manufacturer=" + manufacturer
				+ ", description=" + description + ", category=" + category + ", condition=" + condition + ", price="
				+ price + ", unitsInStock=" + unitsInStock + "]";
>>>>>>> crud:web-jsp/src/main/java/com/sportsbazaar/web/jsp/dto/ProductDTO.java
	}
}
