package com.sportsbazaar.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "category_name" }) })
public class Category extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false, name = "category_name")
	private String categoryName;

	private String description;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Category [categoryName=" + categoryName + ", description=" + description + "]";
	}

}
