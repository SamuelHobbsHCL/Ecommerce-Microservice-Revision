package com.hcl.capstone.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.hcl.capstone.dto.AddressDto;

@Entity
@Table(name = "addresses")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ADDRESS_ID")
	private long addressId;
	
	@Column(name = "STREET", nullable=false, length=255)
	private String street;
	
	@Column(name = "UNIT", nullable=true, length=255)
	private String unit;
	
	@Column(name = "CITY", nullable=false, length=255)
	private String city;
	
	@Column(name = "STATE", nullable=false, length=255)
	private String state;
	
	@Column(name = "COUNTRY", nullable=false, length=255)
	private String country;
	
	@Column(name = "ZIPCODE", nullable=false, length=255)
	private String zipcode;
	
	public Address() {}
	
	public Address(AddressDto address) {
		setStreet(address.getStreet());
		setUnit(address.getUnit());
		setCity(address.getCity());
		setState(address.getState());
		setCountry(address.getCountry());
		setZipcode(address.getZipcode());
	}
	public void setData(AddressDto address) {
		setStreet(address.getStreet());
		setUnit(address.getUnit());
		setCity(address.getCity());
		setState(address.getState());
		setCountry(address.getCountry());
		setZipcode(address.getZipcode());
	}
	public long getAddressId() {
		return addressId;
	}
	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	
}
