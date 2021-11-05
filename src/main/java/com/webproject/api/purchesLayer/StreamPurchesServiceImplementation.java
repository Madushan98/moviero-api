package com.webproject.api.purchesLayer;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webproject.api.entity.Cart;
import com.webproject.api.entity.Movie;
import com.webproject.api.entity.StreamPurcheses;
import com.webproject.api.entity.UserModel;
import com.webproject.api.repository.CartRepository;
import com.webproject.api.repository.CategoryRepository;
import com.webproject.api.repository.MovieRepository;
import com.webproject.api.repository.StreamPurchesRepository;
import com.webproject.api.repository.UserRepository;
import com.webproject.api.shared.Utils;

@Service
public class StreamPurchesServiceImplementation implements StreamPurchesService  {

	@Autowired
	UserRepository userRepository;


	@Autowired
	MovieRepository movieRepository;
	
	

	@Autowired
	CartRepository cartRepository;

	
	@Autowired
	StreamPurchesRepository streamRepository;
	
	
	@Override
	public StreamPurchesDetailResponse getStreamDetails(String userId) {
	
		StreamPurchesDetailResponse streamDetailresponse = new  StreamPurchesDetailResponse() ;
		
		UserModel user = userRepository.findByUserId(userId);
		StreamPurcheses streamPurchesDetail = streamRepository.getById(user.getStreamId()) ;
		
		streamDetailresponse.setUserId(userId);
		
		List <Movie> streamMovies = streamPurchesDetail.getPurches() ;
		
		for(Movie mov : streamMovies) {
			
			StreamMovieResponse streamMovie = new StreamMovieResponse();
			
			BeanUtils.copyProperties(mov,streamMovie );
			
			streamMovie.setMovieVideoUrl(mov.getMovieVideoUrl());
			
//			System.out.println(mov.getMovieVideoUrl());
			
			streamMovie.setMovieCategory(mov.getNameCategory());
			
			streamDetailresponse.addToPurches(streamMovie); 
			
		}
		
		return streamDetailresponse;
	}


	@Override
	public StreamPurchesDetailResponse buyMovies(List<String> movies,String userId) {
          
		
		
		UserModel user = userRepository.findByUserId(userId);
		
		Cart userCart = cartRepository.getById(user.getCartId());
		
	
		
		
		StreamPurcheses streamPurchesDetail = streamRepository.getById(user.getStreamId()) ;
		
		      
		
		for(String movieid : movies) {
		 Movie	movie = movieRepository.getByMovieId(movieid);
		 
		 if(userCart.isMovieInCart(movieid)) {
			 userCart.removefromCart(movieid);
		 }
		
		 
		 if(movie != null && !streamPurchesDetail.isMovieInPurchese(movieid)) {
			 movie.increaceDownloads();
			 movieRepository.save(movie);
			 streamPurchesDetail.addToPurches(movie); 
		
		 }
			
		}
		
		
		StreamPurcheses returnStreamPurchesDetail = streamRepository.save(streamPurchesDetail);
		
		
		
		
		
	StreamPurchesDetailResponse streamDetailresponse = new  StreamPurchesDetailResponse() ;
		
	
		
		streamDetailresponse.setUserId(userId);
		
		List <Movie> streamMovies = streamPurchesDetail.getPurches() ;
		
		for(Movie mov : streamMovies) {
			
			StreamMovieResponse streamMovie = new StreamMovieResponse();
			BeanUtils.copyProperties(mov,streamMovie );
			streamMovie.setMovieVideoUrl(mov.getMovieVideoUrl());
			streamMovie.setMovieCategory(mov.getNameCategory());
			
			
			
			streamDetailresponse.addToPurches(streamMovie); 
			
		}
		
		return streamDetailresponse;
	}

	
	
	
}
