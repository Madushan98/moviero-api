package com.webproject.api.cart;

public interface CartService {

	String AddToCart(String movieId,String userId) throws Exception ;
	String removeFromCart(String movieId,String userId) throws Exception ;
	 
	
	CartDetailResponse getCart(String uerId);
	
	
}
