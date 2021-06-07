package com.sportsbazaar.web.jsp.dto;

public class ProductSummary {

	private long totalProducts;

	private long productsInStock;

	private long productsOutOfStock;

	public long getTotalProducts() {
		return totalProducts;
	}

	public void setTotalProducts(long totalProducts) {
		this.totalProducts = totalProducts;
	}

	public long getProductsInStock() {
		return productsInStock;
	}

	public void setProductsInStock(long productsInStock) {
		this.productsInStock = productsInStock;
	}

	public long getProductsOutOfStock() {
		return productsOutOfStock;
	}

	public void setProductsOutOfStock(long productsOutOfStock) {
		this.productsOutOfStock = productsOutOfStock;
	}
}
