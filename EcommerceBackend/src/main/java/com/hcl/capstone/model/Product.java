package com.hcl.capstone.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCTS")
public class Product {
	public Product(){}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRODUCT_ID")
	private long productId;

	@Column(name = "PRODUCT_NAME")
	private String productName;

	@Column(name = "UNIT_PRICE")
	private double unitPrice;

	@Column(name = "PRODUCT_STOCK")
	private int productStock;

	@Column(name = "PRODUCT_IMAGE")
	private String productImage;

	@Column(name = "PRODUCT_DESCRIPTION")
	private String productDescription;

	public Product(String productName, double unitPrice, int productStock, String productImage, String productDescription){
		this.productName = productName;
		this.unitPrice = unitPrice;
		this.productStock = productStock;
		this.productImage = productImage;
		this.productDescription = productDescription;
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

}
