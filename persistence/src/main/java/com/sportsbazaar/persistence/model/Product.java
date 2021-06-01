package com.sportsbazaar.persistence.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Product extends BaseEntity {

	private static final long serialVersionUID = -3116838651013002599L;

	@Column(nullable = false)
	private String productName;

	@Column(nullable = false)
	private String manufacturer;

	private String description;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private Category category;

	@Column(nullable = false)
	private String condition;

	private BigDecimal price;

	private int unitsInStock;

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
