package com.webproject.api.movie;

import java.util.ArrayList;
import java.util.List;

import com.webproject.api.cart.Cart;
import com.webproject.api.category.Category;
import com.webproject.api.purches.StreamPurcheses;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.webproject.api.exceptions.MovieServiceException;
import com.webproject.api.repository.*;
import com.webproject.api.shared.Utils;

@Service
public class MovieServiceImplamentation implements MovieService {
	@Autowired
	StreamPurchesRepository streamRepository;

	@Autowired
	MovieRepository movieRepository;

	@Autowired
	Utils utils;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	CartRepository cartRepository;

	@Override
	public MovieDto publishMovie(MovieDto movie) throws Exception {

		Movie publishMovie = new Movie();

//		Movie movieAvailable = movieRepository.findByTitle(movie.getTitle());
//		
//		if(movieAvailable != null) {
//			
//			throw new MovieServiceException("Movie is Already Available");
//		}

		BeanUtils.copyProperties(movie, publishMovie);
		
		

		String movieId = utils.generateUserId(25);

		publishMovie.setMovieId(movieId);

		Category getCategory = categoryRepository.getByCategoryName(movie.getMovieCategory());

		publishMovie.setCategory(getCategory);

		publishMovie.setDownloads(0);

		Movie storedMovie = movieRepository.save(publishMovie);

		MovieDto returnMovie = new MovieDto();
		BeanUtils.copyProperties(storedMovie, returnMovie);

		returnMovie.setMovieCategory(storedMovie.getNameCategory());

		return returnMovie;
	}

	@Override
	public MovieDto getMovieDetail(String movieId) {

		MovieDto returnMovie = new MovieDto();

		Movie movie = movieRepository.getByMovieId(movieId);

		BeanUtils.copyProperties(movie, returnMovie);

		returnMovie.setMovieCategory(movie.getNameCategory());

		return returnMovie;
	}

	@Override
	public List<MovieDto> getMovies() {

		List<MovieDto> returnMovies = new ArrayList<MovieDto>();

		List<Movie> movies = movieRepository.findAll();

		for (Movie movieEntity : movies) {
			MovieDto movie = new MovieDto();
			
	
			BeanUtils.copyProperties(movieEntity, movie);
			String category = movieEntity.getNameCategory() ;
			
			movie.setMovieCategory(category);
			
			

			returnMovies.add(movie);
		}

		
		
		return returnMovies;
	}

	@Override
	public List<MovieDto> findByTitle(int page, int limit, String sortBy, String title) {

		List<Movie> movieResult = movieRepository.findByTitle(title);

		List<MovieDto> returnMovies = new ArrayList<MovieDto>();

		for (Movie movieEntity : movieResult) {
			MovieDto movie = new MovieDto();
			BeanUtils.copyProperties(movieEntity, movie);
			movie.setMovieCategory(movieEntity.getNameCategory());

			returnMovies.add(movie);
		}

		return returnMovies;
	}

	@Override
	public List<MovieDto> findByCategory(String category) {
		// TODO Auto-generated method stub

		Category categoryResult = categoryRepository.getByCategoryName(category);

		List<Movie> movieResult = movieRepository.findByCategory(categoryResult);

		List<MovieDto> returnMovies = new ArrayList<MovieDto>();

		for (Movie movieEntity : movieResult) {
			MovieDto movie = new MovieDto();
			BeanUtils.copyProperties(movieEntity, movie);
			movie.setMovieCategory(movieEntity.getNameCategory());

			returnMovies.add(movie);
		}

		return returnMovies;
	}

	@Override
	public String deleteMovieDetail(String movieId) throws Exception {

		Movie movie = movieRepository.findByMovieId(movieId);

		if (movie == null) {
			throw new MovieServiceException("Movie is Not Valid");
		}

		for (Cart cart : movie.getCarts()) {
			cart.removefromCart(movieId);
			cart.removeFromTotal(movie.getMoviePrice());
			cartRepository.save(cart);
		}

		for (StreamPurcheses purchese : movie.getPurcheses()) {
			purchese.removefromPurchese(movieId);

			streamRepository.save(purchese);
		}

		movieRepository.deleteById(movie.getId());

		return "Movie Succesfully Deleted";
	}

	@Override
	public List<MovieDto> getLatestMovies() {

		List<Movie> movies = movieRepository.findAll(Sort.by("releaseDate").descending());

		List<MovieDto> returnMovies = new ArrayList<MovieDto>();
		for (Movie movieEntity : movies) {
			MovieDto movie = new MovieDto();
			BeanUtils.copyProperties(movieEntity, movie);
			movie.setMovieCategory(movieEntity.getNameCategory());

			returnMovies.add(movie);
		}

		return returnMovies;
	}

	@Override
	public List<MovieDto> getNewLyAddedMovies() {

		List<Movie> movies = movieRepository.findAll(Sort.by("addToMoviesDate").descending());

		List<MovieDto> returnMovies = new ArrayList<MovieDto>();
		for (Movie movieEntity : movies) {
			MovieDto movie = new MovieDto();
			BeanUtils.copyProperties(movieEntity, movie);
			movie.setMovieCategory(movieEntity.getNameCategory());

			returnMovies.add(movie);
		}

		return returnMovies;
	}

	

	@Override
	public List<MovieDto> getMostDownloadMovies() {

		List<Movie> movies = movieRepository.findAll(Sort.by("downloads").descending());

		List<MovieDto> returnMovies = new ArrayList<MovieDto>();
		for (Movie movieEntity : movies) {
			MovieDto movie = new MovieDto();
			BeanUtils.copyProperties(movieEntity, movie);
			movie.setMovieCategory(movieEntity.getNameCategory());

			returnMovies.add(movie);
		}

		return returnMovies;
	}
	
	
	@Override
	public MovieDto updateMovie(MovieDto movieCopy, String id) throws Exception {

		MovieDto updatedMovie = new MovieDto();

		Movie movieModel = movieRepository.findByMovieId(id);

		if (movieModel == null) {
			throw new MovieServiceException("Movie is Not Valid");
		}

		Category category = categoryRepository.getByCategoryName(movieCopy.getMovieCategory());

		movieModel.setTitle(movieCopy.getTitle());
		movieModel.setDescription(movieCopy.getDescription());
		movieModel.setImdbRating(movieCopy.getImdbRating());
		movieModel.setMoviePrice(movieCopy.getMoviePrice());
		movieModel.setCategory(category);

		Movie updatedDetails = movieRepository.save(movieModel);

		MovieDto returnMovie = new MovieDto();

		BeanUtils.copyProperties(updatedDetails, returnMovie);

		returnMovie.setMovieCategory(updatedDetails.getNameCategory());

		return returnMovie;
	}
	
	
	

}
