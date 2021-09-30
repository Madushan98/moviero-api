package com.webproject.api.cartLayer;

import java.util.List;

import com.webproject.api.movieLayer.MovieDto;

public interface CartService {

	String AddToCart(String movieId,String userId) throws Exception ;
	
	
	
	String removeFromCart(String movieId,String userId) throws Exception ;
	 
	
	CartDetailResponse getCart(String uerId);
	
	
}
