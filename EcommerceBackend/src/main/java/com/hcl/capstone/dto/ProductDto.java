package com.hcl.capstone.dto;

public class ProductDto {
	private String dtoName;
	private double dtoPrice;
	private int dtoStock;
	private String dtoImage;
	private String dtoDescription;
	long id;

	public ProductDto(String productName, double unitPrice, int productStock, String productImage,
			String productDescription) {
		this.dtoName = productName;
		this.dtoPrice = unitPrice;
		this.dtoStock = productStock;
		this.dtoImage = productImage;
		this.dtoDescription = productDescription;
	}

	public String getDtoName() {
		return dtoName;
	}

	public void setDtoName(String productName) {
		this.dtoName = productName;
	}

	public double getDtoPrice() {
		return dtoPrice;
	}

	public void setDtoPrice(double unitPrice) {
		this.dtoPrice = unitPrice;
	}

	public int getDtoStock() {
		return dtoStock;
	}

	public void setDtoStock(int productStock) {
		this.dtoStock = productStock;
	}

	public String getDtoImage() {
		return dtoImage;
	}

	public void setDtoImage(String productImage) {
		this.dtoImage = productImage;
	}

	public String getDtoDescription() {
		return dtoDescription;
	}

	public void setDtoDescription(String productDescription) {
		this.dtoDescription = productDescription;
	}

	public void setDtoId(long id) {
		this.id = id;
	}

	public Long getDtoId() {
		return id;
	}
}
