package com.webproject.api.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webproject.api.entity.*;
import com.webproject.api.repository.*;
import com.webproject.api.userLayer.*;
import com.webproject.api.movieLayer.*;


@RestController
@RequestMapping("movies")
public class movieController {

	@Autowired
	MovieService movieService ;
	
	@Autowired
	MovieRepository movieRepository ;
	
	

@GetMapping	
public List<MovieDetailsResponse> Getmovies(@RequestParam(value="page", defaultValue="0") int page, @RequestParam(value="limit", defaultValue="20") int limit) {
	
	List<MovieDetailsResponse> returnMovies = new ArrayList<>();
	
	List<MovieDto> movies = movieService.getMovies(page,limit);
	
	
	for (MovieDto movieDto : movies) {
		MovieDetailsResponse movie = new MovieDetailsResponse() ;
		BeanUtils.copyProperties(movieDto, movie);
		returnMovies.add(movie);
	}
	
	
	
	return returnMovies ;
}



@PostMapping
public MovieDetailsResponse CreateMovie(@RequestBody MovieDetailRequest movieDetail) {
	
	MovieDetailsResponse movieRest = new MovieDetailsResponse();	
	
	MovieDto movieCopy = new MovieDto() ;
	BeanUtils.copyProperties(movieDetail, movieCopy);

	movieCopy.setAddToMoviesDate(LocalDate.now());
	
	MovieDto returnMovie = movieService.publishMovie(movieCopy);
	
	
	
	

	BeanUtils.copyProperties(returnMovie, movieRest);
	
	movieRest.setMovieCategory(returnMovie.getMovieCategory());
	
	

	return movieRest ;
}
	
	
}
