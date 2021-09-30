package com.webproject.api.purchesLayer;

import java.time.LocalDate;

import com.webproject.api.entity.Cart;

public class StreamMovieResponse {

	private static final long serialVersionUID = -4652777307512175027L;

	private long id;

	private String movieId;

	private String movieVideoUrl;

	private String movieBanerUrl;

	

	private String movieImageUrl;

	private String title;

	private String description;

	private String movieCategory;

	private double imdbRating;



	private LocalDate releaseDate;

	private LocalDate addToMoviesDate;

	private int downloads;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getMovieVideoUrl() {
		return movieVideoUrl;
	}

	public void setMovieVideoUrl(String movieVideoUrl) {
		this.movieVideoUrl = movieVideoUrl;
	}

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

	public String getMovieCategory() {
		return movieCategory;
	}

	public void setMovieCategory(String movieCategory) {
		this.movieCategory = movieCategory;
	}

	public double getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(double imdbRating) {
		this.imdbRating = imdbRating;
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

	public int getDownloads() {
		return downloads;
	}

	public void setDownloads(int downloads) {
		this.downloads = downloads;
	}
	
	
	
	
}
