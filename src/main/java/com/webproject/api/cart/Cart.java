package com.webproject.api.cart;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.webproject.api.user.UserModel;
import com.webproject.api.movie.Movie;

@Entity
@Table(name = "cart")
public class Cart {

	public Cart() {
		Total = 0.00;
		inCart = new ArrayList<Movie>();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@OneToOne(mappedBy = "cart")
	@JsonIgnoreProperties(value = { "cart", "handler", "hibernateLazyInitializer" }, allowSetters = true)
	private UserModel user;

	@ManyToMany()
	@JoinTable(name = "movie_incart", joinColumns = @JoinColumn(name = "movieId"), inverseJoinColumns = @JoinColumn(name = "cartId"))
	private List<Movie> inCart;

	@Column(nullable = false)
	private Double Total;

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public Double getTotal() {
		return Total;
	}

	public List<Movie> getInCart() {
		return inCart;
	}

	public void setInCart(List<Movie> inCart) {
		this.inCart = inCart;
	}

	public void addToCart(Movie movie) {
		inCart.add(movie);
	}

	public Double addToTotal(Double moviePrice) {

		this.Total += moviePrice;

		return Total;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean isMovieInCart(String movieId) {

		for (Movie movie : this.inCart) {

			String id = movie.getMovieId();

			if (id.equals(movieId)) {
				
				return true;
			}

		}

		return false;

	}

	public void removefromCart(String movieId) {

		List<Movie> newCart = new ArrayList<>();

		for (Movie movie : this.inCart) {

			String id = movie.getMovieId();

			if (!id.equals(movieId)) {
				
				newCart.add(movie);

			}else {
				
				this.Total -= movie.getMoviePrice() ;
			}

		}

		this.inCart = newCart;

	}

	public void setTotal(Double total) {
		Total = total;
	}

	public Double removeFromTotal(Double moviePrice) {

		this.Total -= moviePrice;

		return Total;
	}
}
