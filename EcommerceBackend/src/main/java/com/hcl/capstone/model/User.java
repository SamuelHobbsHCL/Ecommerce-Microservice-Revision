package com.hcl.capstone.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.hcl.capstone.dto.AddressDto;
import com.hcl.capstone.model.enumeration.AuthProvider;

@Entity
@Table(name = "USERS")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "USER_ID")
	private long userId;
	
	@Column(name="USER_NAME", nullable=false, length=255)
	private String userName;
	
	@Column(name="PASSWORD", nullable=false, length=255)
	private String password;
	
	@Column(name="FIRST_NAME", nullable=false, length=255)
	private String firstName;
	
	@Column(name="LAST_NAME", nullable=false, length=255)
	private String lastName;
	
	@Column(name="EMAIL", nullable=false, length=255)
	private String email;
	
	@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ADDRESS_ID", nullable = true)
	private Address address;
	
	@Column(name = "PROFILE_IMAGE")
	private String profileImage;

	@ManyToMany(cascade = {
			CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST}, fetch = FetchType.EAGER)
	@JoinTable(
		name = "USER_ROLES",
		joinColumns = @JoinColumn(name = "USER_ID"),
		inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	private Set<Roles> roles;
		
	@Enumerated(EnumType.STRING)
	@Column(name="AUTH_PROVIDER", length = 15)
	private AuthProvider authProvider;
	
	public User(){}
	
	public User(String userName, String password, String firstName, String lastName, String email) {
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;		
	}
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}

	public AuthProvider getAuthProvider() {
		return authProvider;
	}

	public void setAuthProvider(AuthProvider authProvider) {
		this.authProvider = authProvider;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	public void setAddressByDto(AddressDto addressDto) {
		if (this.address == null)
			this.address = new Address(addressDto);
		else
			this.address.setData(addressDto);
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	

}
