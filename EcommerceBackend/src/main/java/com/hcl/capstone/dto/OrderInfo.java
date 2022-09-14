package com.hcl.capstone.dto;

public class OrderInfo {
	AddressDto shippingAddress;
	AddressDto billingAddress;
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
}
