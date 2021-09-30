package com.webproject.api.entity;


import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity()

public class UserModel  implements Serializable {

	private static final long serialVersionUID = -4652777307512175027L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

	@Column(nullable = false)
	private String userId;
	
	
	

	@Column(nullable = false, length = 50)
	private String userName;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", referencedColumnName = "ID")
    private Cart cart;
	
	

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "purches_id", referencedColumnName = "ID")
    private StreamPurcheses purcheses;
	
	@Column(nullable = false, length = 120, unique = true)
	private String email;

	@Column(nullable = false)
	private String encryptedPassword;

	@Column(nullable = true)
	private String firstName;
	


	@Column(nullable = true)
	private String lastName;
	

	


	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = {
			@JoinColumn(name = "USER_ID", referencedColumnName = "ID") }, inverseJoinColumns = {
					@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID") })
	@JsonIgnoreProperties(value = {"users", "handler","hibernateLazyInitializer"}, allowSetters = true)
	private List<Role> roles;

	public long getId() {
		return id;
	}


	public String getUserName() {
		return userName;
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



	public void setUserName(String userName) {
		this.userName = userName;
	}

    

	public UserModel() {
		
		cart = new Cart();
		purcheses = new StreamPurcheses();
	}
	
	
	
	public Cart getCart() { 
		return cart;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	
	public List<Role> getRoles() {
		return roles;
	}

	

	public void setId(long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	
	
	public Long getCardId() {
		return cart.getId() ;
	}
	
	
	public Long getStreamId() {
		return purcheses.getId() ;
	}
	
	@Override
	public String toString() {
		return "UserModel [id=" + id + ", userId=" + userId + ", userName=" + userName + ", cart=" + cart + ", email="
				+ email + ", encryptedPassword=" + encryptedPassword + ", firstName=" + firstName + ", lastName="
				+ lastName + ", roles=" + roles + "]";
	}

}
