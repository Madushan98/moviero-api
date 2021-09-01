package com.webproject.api.entity;


import java.util.ArrayList;
import java.util.List;

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
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "cart")
public class Cart {
	
	
	public Cart() {
		Total = 0.00 ;
		inCart = new ArrayList<Movie>();
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
	
	
	

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public Double getTotal() {
		return Total;
	}

	public void setTotal(Double total) {
		Total = total;
	}

	public List<Movie> getInCart() {
		return inCart;
	}

	public void setInCart(List<Movie> inCart) {
		this.inCart = inCart;
	}

	@OneToOne(mappedBy = "cart")
	@JsonIgnoreProperties(value = {"cart", "handler","hibernateLazyInitializer"}, allowSetters = true)
	private UserModel user;
	
    
    
	@Column(nullable = false)
	private Double Total ;
	
	  @ManyToMany(cascade = CascadeType.ALL)
	    @JoinTable(name = "movie_incart", 
	      joinColumns = @JoinColumn(name = "movieId"), 
	      inverseJoinColumns = @JoinColumn(name = "cartId"
	      ))
	    private List<Movie> inCart;
	
	  public void addToCart(Movie movie) {
		  inCart.add(movie);
	  }
	  
	  
	  
	  public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}
}
