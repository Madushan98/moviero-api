package com.webproject.api.movieLayer;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;



public class MovieDetailRequest {

	private String description;

	private double imdbRating ;

	private Double moviePrice;

	private String movieUrl;

	private String title;
	
	private String movieCategory;
	
	private String movieBanerUrl;

	private String movieImageUrl;
	
	private String movieVideoUrl;

	public String getMovieBanerUrl() {
		return movieBanerUrl;
	}

	public void setMovieBanerUrl(String movieBanerUrl) {
		this.movieBanerUrl = movieBanerUrl;
	}

	public String getMovieImageUrl() {
		return movieImageUrl;
	}

	public void setMovieImageUrl(String movieImageUrl) {
		this.movieImageUrl = movieImageUrl;
	}

	public String getMovieVideoUrl() {
		return movieVideoUrl;
	}

	public void setMovieVideoUrl(String movieVideoUrl) {
		this.movieVideoUrl = movieVideoUrl;
	}

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate releaseDate;

	
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

	public String getMovieUrl() {
		return movieUrl;
	}

	public void setMovieUrl(String movieUrl) {
		this.movieUrl = movieUrl;
	}

	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public double getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(double imdbRating) {
		this.imdbRating = imdbRating;
	}

}
