package com.hcl.capstone.dto;

import java.util.Set;

import com.hcl.capstone.model.Category;

public class ProductDto {
	private long productId;
	private String productName;
	private double unitPrice;
	private int productStock;
	private int stockThreshold;
	private String productImage;
	private String productDescription;
	private Set<Category> categories;

	public ProductDto(long productId, String productName, double unitPrice, int productStock, int stockThreshold,
			String productImage, String productDescription, Set<Category> categories) {
		this.productId = productId;
		this.productName = productName;
		this.unitPrice = unitPrice;
		this.productStock = productStock;
		this.stockThreshold = stockThreshold;
		this.productImage = productImage;
		this.productDescription = productDescription;
		this.categories = categories;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getProductStock() {
		return productStock;
	}

	public void setProductStock(int productStock) {
		this.productStock = productStock;
	}

	public int getStockThreshold() {
		return stockThreshold;
	}

	public void setStockThreshold(int stockThreshold) {
		this.stockThreshold = stockThreshold;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

}
