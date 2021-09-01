package com.webproject.api.movieLayer;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.webproject.api.entity.Cart;


public class MovieDto implements Serializable {

	private static final long serialVersionUID = -4652777307512175027L;

	private long id;

	private String movieId;

	private String movieUrl;

	private String title;

	private String description;
	
	private String movieCategory ;
	
	private double imdbRating ;
	
	

	private Cart cart;

	private LocalDate releaseDate;

	private LocalDate addToMoviesDate;

	public String getMovieCategory() {
		return movieCategory;
	}

	public void setMovieCategory(String movieCategory) {
		this.movieCategory = movieCategory;
	}

	private Double moviePrice;


	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public LocalDate getAddToMoviesDate() {
		return addToMoviesDate;
	}

	public void setAddToMoviesDate(LocalDate addToMoviesDate) {
		this.addToMoviesDate = addToMoviesDate;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getMovieUrl() {
		return movieUrl;
	}

	public void setMovieUrl(String movieUrl) {
		this.movieUrl = movieUrl;
	}


	public Double getMoviePrice() {
		return moviePrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setMoviePrice(Double moviePrice) {
		this.moviePrice = moviePrice;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(double imdbRating) {
		this.imdbRating = imdbRating;
	}

}
