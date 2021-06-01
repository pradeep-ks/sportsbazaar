package com.sportsbazaar.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "categoryName" }) })
public class Category extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private String categoryName;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Category [categoryName=").append(categoryName).append(", id=").append(id).append("]");
		return builder.toString();
	}

}
