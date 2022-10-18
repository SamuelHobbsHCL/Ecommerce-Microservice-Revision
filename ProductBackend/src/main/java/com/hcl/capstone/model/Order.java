package com.hcl.capstone.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERS")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ORDER_ID")
	private long orderId;
	
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
	private User user;
    
    @OneToMany(cascade = CascadeType.ALL, targetEntity = OrderItem.class)
    @JoinColumn(name="ORDER_ID", referencedColumnName = "ORDER_ID")
    private List<OrderItem> cartItems;
	
	@Column(name = "ORDER_DATE")
	private Date orderDate;
	
	@Column(name = "ORDER_TOTAL")
	private double orderTotal;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SHIPPING_ADDRESS_ID", referencedColumnName = "ADDRESS_ID", nullable = true)
	private Address shippingAddress;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "BILLING_ADDRESS_ID", referencedColumnName = "ADDRESS_ID", nullable = true)
	private Address billingAddress;
    
    @Column(name = "ORDER_STATUS")
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


	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
    
}
