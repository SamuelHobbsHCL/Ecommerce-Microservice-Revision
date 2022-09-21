package com.hcl.capstone.dto;

import java.util.Date;
import java.util.List;
import com.hcl.capstone.model.OrderItem;
import com.hcl.capstone.model.User;

public class OrderDto {
	
	private long id;
	String status;
	AddressDto shippingAddress;
	AddressDto billingAddress;
	private User dtoUser;
	private List<OrderItem> dtoCartItems;
	private Date dtoDate;
	private double dtoTotal;
	private String dtoStatus;
	
	public OrderDto(String status) {
		this.status = status;
	}

	public String getDtoStatus() {
		return dtoStatus;
	}

	public void setDtoStatus(String dtoStatus) {
		this.dtoStatus = dtoStatus;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public AddressDto getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(AddressDto shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public AddressDto getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(AddressDto billingAddress) {
		this.billingAddress = billingAddress;
	}

	public User getDtoUser() {
		return dtoUser;
	}

	public void setDtoUser(User dtoUser) {
		this.dtoUser = dtoUser;
	}

	public List<OrderItem> getDtoCartItems() {
		return dtoCartItems;
	}

	public void setDtoCartItems(List<OrderItem> dtoCartItems) {
		this.dtoCartItems = dtoCartItems;
	}

	public Date getDtoDate() {
		return dtoDate;
	}

	public void setDtoDate(Date dtoDate) {
		this.dtoDate = dtoDate;
	}

	public double getDtoTotal() {
		return dtoTotal;
	}

	public void setDtoTotal(double dtoTotal) {
		this.dtoTotal = dtoTotal;
	}

}
