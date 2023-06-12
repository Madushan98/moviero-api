package com.webproject.api.cart;

import java.util.List;

import com.webproject.api.movie.MovieDetailsResponse;

public class CartDetailResponse {

	private Double Total;

	private List<MovieDetailsResponse> moviesInCart;

	private String userId  ;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<MovieDetailsResponse> getMoviesInCart() {
		return moviesInCart;
	}

	public void setMoviesInCart(List<MovieDetailsResponse> moviesInCart) {
		this.moviesInCart = moviesInCart;
	}

	public Double getTotal() {
		return Total;
	}

	public void setTotal(Double total) {
		Total = total;

	}

}
