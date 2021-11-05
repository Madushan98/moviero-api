package com.webproject.api.cartLayer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webproject.api.entity.Cart;
import com.webproject.api.entity.Movie;
import com.webproject.api.entity.StreamPurcheses;
import com.webproject.api.entity.UserModel;
import com.webproject.api.exceptions.MovieServiceException;
import com.webproject.api.movieLayer.MovieDetailsResponse;
import com.webproject.api.movieLayer.MovieDto;
import com.webproject.api.movieLayer.MovieService;
import com.webproject.api.repository.CartRepository;
import com.webproject.api.repository.MovieRepository;
import com.webproject.api.repository.StreamPurchesRepository;
import com.webproject.api.repository.UserRepository;

@Service
public class CartServiceImplementation implements CartService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	MovieRepository movieRepository;

	@Autowired
	CartRepository cartRepository;

	@Autowired
	StreamPurchesRepository streamRepository;

	@Override
	public String AddToCart(String movieId, String userId) throws Exception {

		Movie movie = movieRepository.findByMovieId(movieId);

		UserModel user = userRepository.findByUserId(userId);

		Cart cart = cartRepository.getById(user.getCartId());

		StreamPurcheses streamAcount = streamRepository.getById(user.getStreamId());

		if (cart.isMovieInCart(movieId)) {
			return "Movie is already in the cart";
		} else if (streamAcount.isMovieInPurchese(movieId)) {
			return "Movie is Already Purchesed";
		}

		cart.addToCart(movie);
		cart.addToTotal(movie.getMoviePrice());

		cartRepository.save(cart);

		return "Movie Succesfully Added to The Cart";
	}

	@Override
	public String removeFromCart(String movieId, String userId) throws Exception {
		Movie movie = movieRepository.findByMovieId(movieId);

		UserModel user = userRepository.findByUserId(userId);

		Cart cart = cartRepository.getById(user.getCartId());

		if (!cart.isMovieInCart(movieId)) {
			throw new MovieServiceException("Movie is Not In the Cart");
		}

		cart.removefromCart(movieId);

		if ((cart.getInCart()).isEmpty()) {
			cart.setTotal(0.0);
		} else {
			

		}

		cartRepository.save(cart);
		return "Movie Succesfully Removed From The Cart";
	}

	@Override
	public CartDetailResponse getCart(String userId) {

		UserModel user = userRepository.findByUserId(userId);

		Cart cart = cartRepository.getById(user.getCartId());

		CartDetailResponse cartDetailResponse = new CartDetailResponse();

		List<MovieDto> movieDtos = new ArrayList<MovieDto>();

		List<Movie> moviesInCart = cart.getInCart();

		for (Movie movieEntity : moviesInCart) {
			MovieDto movie = new MovieDto();
			BeanUtils.copyProperties(movieEntity, movie);
			movie.setMovieCategory(movieEntity.getNameCategory());

			movieDtos.add(movie);
		}

		List<MovieDetailsResponse> movieDetailResponseInCart = new ArrayList<>();

		for (MovieDto movieEntity : movieDtos) {
			MovieDetailsResponse movieDetailResonse = new MovieDetailsResponse();
			BeanUtils.copyProperties(movieEntity, movieDetailResonse);

			movieDetailResponseInCart.add(movieDetailResonse);
		}

		cartDetailResponse.setUserId(userId);
		cartDetailResponse.setMoviesInCart(movieDetailResponseInCart);
		cartDetailResponse.setTotal(cart.getTotal());

		return cartDetailResponse;
	}

}
