package com.webproject.api.movieLayer;

import java.time.LocalDate;



public class MovieDetailsResponse {

	private long id;

	private String movieId;

	private String title;

	private String description;

	private Double moviePrice;

	private LocalDate releaseDate;

	private LocalDate addToMoviesDate;
	
	private double imdbRating ;
	
	private String movieCategory ;

	public String getMovieCategory() {
		return movieCategory;
	}

	public void setMovieCategory(String movieCategory) {
		this.movieCategory = movieCategory;
	}
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

	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getMoviePrice() {
		return moviePrice;
	}

	public void setMoviePrice(Double moviePrice) {
		this.moviePrice = moviePrice;
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
