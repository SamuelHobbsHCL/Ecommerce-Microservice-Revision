package com.hcl.capstone.dto;

public class ProductDto {
	private String productName;

	private double unitPrice;

	private int productStock;

	private String productImage;

	private String productDescription;

	long id;

	public ProductDto(String productName, double unitPrice, int productStock, String productImage,
			String productDescription) {
		this.productName = productName;
		this.unitPrice = unitPrice;
		this.productStock = productStock;
		this.productImage = productImage;
		this.productDescription = productDescription;
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

	public void setProductId(long id) {
		this.id = id;
	}

	public Long getProductId() {
		return id;
	}
}
