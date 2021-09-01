package com.webproject.api.movieLayer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.webproject.api.entity.*;
import com.webproject.api.repository.*;
import com.webproject.api.shared.Utils;
import com.webproject.api.userLayer.*;

@Service
public class MovieServiceImplamentation  implements MovieService{
	
	
	@Autowired
	MovieRepository movieRepository ;
	
	@Autowired
	Utils utils;

	@Autowired
	CategoryRepository categoryRepository ;
	
	
	@Override
	public MovieDto publishMovie(MovieDto movie) {
		
		Movie publishMovie = new Movie();
		BeanUtils.copyProperties(movie, publishMovie);

		String movieId = utils.generateUserId(25); 

		publishMovie.setMovieId(movieId);
		
		Category getCategory = categoryRepository.getCategoryByCategoryName(movie.getMovieCategory());
		
		
		
		publishMovie.setCategory(getCategory);
		
		
		
		
		
		Movie storedMovie = movieRepository.save(publishMovie);

		MovieDto returnMovie = new MovieDto();
		BeanUtils.copyProperties(storedMovie, returnMovie);
		
		
		
		returnMovie.setMovieCategory(storedMovie.getNameCategory());

		return returnMovie;
	}

	
	@Override
	public MovieDto getMovieDetail(String movieId) {
		
		
		return null;
	}


	@Override
	public List<MovieDto> getMovies(int page, int limit) {
		
		List <MovieDto> returnMovies = new ArrayList<MovieDto>() ;
		
		Pageable pageableRequest = PageRequest.of(page, limit);
		
		Page<Movie> moviePage = movieRepository.findAll(pageableRequest);
		
		List<Movie> movies = moviePage.getContent();
		
		for (Movie movieEntity : movies) {
			MovieDto movie = new MovieDto() ;
			BeanUtils.copyProperties(movieEntity, movie);
			movie.setMovieCategory(movieEntity.getNameCategory());
			
			returnMovies.add(movie);
		}
		
		return returnMovies;
	}
	
	
	
}
