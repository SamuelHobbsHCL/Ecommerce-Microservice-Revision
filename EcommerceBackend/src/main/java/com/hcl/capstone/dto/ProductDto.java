package com.hcl.capstone.dto;

import java.util.Set;

import com.hcl.capstone.model.Category;

public class ProductDto {
	private long productIdDto;
	private String productNameDto;
	private double unitPriceDto;
	private int productStockDto;
	private int stockThresholdDto;
	private String productImageDto;
	private String productDescriptionDto;
	private Set<Category> categoriesDto;

	public ProductDto(long productId, String productName, double unitPrice, int productStock, int stockThreshold,
			String productImage, String productDescription, Set<Category> categories) {
		this.productIdDto = productId;
		this.productNameDto = productName;
		this.unitPriceDto = unitPrice;
		this.productStockDto = productStock;
		this.stockThresholdDto = stockThreshold;
		this.productImageDto = productImage;
		this.productDescriptionDto = productDescription;
		this.categoriesDto = categories;
	}

	public long getProductIdDto() {
		return productIdDto;
	}

	public void setProductIdDto(long productIdDto) {
		this.productIdDto = productIdDto;
	}

	public String getProductNameDto() {
		return productNameDto;
	}

	public void setProductNameDto(String productNameDto) {
		this.productNameDto = productNameDto;
	}

	public double getUnitPriceDto() {
		return unitPriceDto;
	}

	public void setUnitPriceDto(double unitPriceDto) {
		this.unitPriceDto = unitPriceDto;
	}

	public int getProductStockDto() {
		return productStockDto;
	}

	public void setProductStockDto(int productStockDto) {
		this.productStockDto = productStockDto;
	}

	public int getStockThresholdDto() {
		return stockThresholdDto;
	}

	public void setStockThresholdDto(int stockThresholdDto) {
		this.stockThresholdDto = stockThresholdDto;
	}

	public String getProductImageDto() {
		return productImageDto;
	}

	public void setProductImageDto(String productImageDto) {
		this.productImageDto = productImageDto;
	}

	public String getProductDescriptionDto() {
		return productDescriptionDto;
	}

	public void setProductDescriptionDto(String productDescriptionDto) {
		this.productDescriptionDto = productDescriptionDto;
	}

	public Set<Category> getCategoriesDto() {
		return categoriesDto;
	}

	public void setCategoriesDto(Set<Category> categoriesDto) {
		this.categoriesDto = categoriesDto;
	}


}
