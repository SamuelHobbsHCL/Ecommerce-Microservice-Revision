package com.hcl.capstone.dto;

import java.util.Date;
import java.util.List;
import com.hcl.capstone.model.OrderItem;
import com.hcl.capstone.model.User;

public class OrderDto {

	String status;
	AddressDto shippingAddress;
	AddressDto billingAddress;
	private long orderId;
	private User user;
	private List<OrderItem> cartItems;
	private Date orderDate;
	private double orderTotal;
	private String orderStatus;

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<OrderItem> cartItems) {
		this.cartItems = cartItems;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public double getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(double orderTotal) {
		this.orderTotal = orderTotal;
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

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public OrderDto(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
