package com.hcl.capstone.dto;

import java.util.Date;
import java.util.List;
import com.hcl.capstone.model.OrderItem;
import com.hcl.capstone.model.User;

public class OrderDto {

	private long id;
	private AddressDto shippingAddress;
	private AddressDto billingAddress;
	private User dtoUser;
	private List<OrderItem> dtoCartItems;
	private Date dtoDate;
	private double dtoTotal;
	private String dtoStatus;

	public OrderDto(AddressDto shippingAddress, AddressDto billingAddress, User dtoUser,
			List<OrderItem> dtoCartItems, Date dtoDate, double dtoTotal, String dtoStatus) {
		super();
		this.shippingAddress = shippingAddress;
		this.billingAddress = billingAddress;
		this.dtoUser = dtoUser;
		this.dtoCartItems = dtoCartItems;
		this.dtoDate = dtoDate;
		this.dtoTotal = dtoTotal;
		this.dtoStatus = dtoStatus;
	}
	
	public OrderDto(String dtoStatus) {
		super();
		this.dtoStatus = dtoStatus;
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
