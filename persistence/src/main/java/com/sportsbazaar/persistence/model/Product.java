package com.sportsbazaar.persistence.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class Product extends BaseEntity {

	private static final long serialVersionUID = -3116838651013002599L;

	@NotBlank(message = "Product name is required")
	@Column(nullable = false)
	private String productName;

	@NotBlank(message = "Product manfacturer is required")
	@Column(nullable = false)
	private String manufacturer;

	@NotBlank(message = "Product description is required")
	private String description;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private Category category;

	@NotBlank(message = "Product condition is required")
	@Column(nullable = false)
	private String condition;

	@Min(value = 10, message = "Price must be greater than 10")
	private BigDecimal price;

	@Min(value = 0, message = "Units in stock must be 0 or greater")
	private int unitsInStock;
	
	@Transient
	private MultipartFile image;

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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Product [productName=").append(productName).append(", manufacturer=").append(manufacturer)
				.append(", description=").append(description).append(", category=").append(category)
				.append(", condition=").append(condition).append(", price=").append(price).append(", unitsInStock=")
				.append(unitsInStock).append(", id=").append(id).append("]");
		return builder.toString();
	}
}
