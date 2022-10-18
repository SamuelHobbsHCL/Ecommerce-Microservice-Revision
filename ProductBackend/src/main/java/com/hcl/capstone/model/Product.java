package com.hcl.capstone.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.hcl.capstone.dto.ProductDto;

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
	
	@Column(name = "STOCK_THRESHOLD")
	private int stockThreshold;
	
	@ManyToMany(cascade = {
			
            CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinTable(
		name = "PRODUCT_CATEGORIES",
		joinColumns = @JoinColumn(name = "PRODUCT_ID"),
		inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID"))
	private Set<Category> categories;

	public Product(String productName, double unitPrice, int productStock, int stockThreshold, String productImage, String productDescription){
		this.productName = productName;
		this.unitPrice = unitPrice;
		this.productStock = productStock;
		this.stockThreshold = stockThreshold;
		this.productImage = productImage;
		this.productDescription = productDescription;
	}

	public long getProductId() {
		return productId;
	}

	public Product(ProductDto productDTO) {
		this.productId = productDTO.getProductIdDto();
		this.productName = productDTO.getProductNameDto();
		this.unitPrice = productDTO.getUnitPriceDto();
		this.productStock = productDTO.getProductStockDto();
		this.productImage = productDTO.getProductImageDto();
		this.productDescription = productDTO.getProductDescriptionDto();
		this.stockThreshold = productDTO.getStockThresholdDto();
		this.categories = productDTO.getCategoriesDto();
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

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public int getStockThreshold() {
		return stockThreshold;
	}

	public void setStockThreshold(int stockThreshold) {
		this.stockThreshold = stockThreshold;
	}
	
}
