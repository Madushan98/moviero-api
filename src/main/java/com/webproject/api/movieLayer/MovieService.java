package com.webproject.api.movieLayer;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetailsService;





public interface MovieService  {

	MovieDto publishMovie(MovieDto movie);
	
	MovieDto getMovieDetail(String movieId);

	List<MovieDto> getMovies(int page, int limit); 
	
	
	
	
}
