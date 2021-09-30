package com.webproject.api.entity;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

@Entity
public class Movie implements Serializable {

	private static final long serialVersionUID = -4652777307512175027L;

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	private String movieId;


	private String movieVideoUrl;

	
	private String movieBanerUrl;


	private String movieImageUrl;

	@Column(nullable = false, length = 50)
	private String title;

	@Column(columnDefinition = "TEXT", nullable = false, length = 4000)
	private String description;

	@Column(nullable = false)
	private Double moviePrice;

	@Column(nullable = false)
	private int downloads;

	@Column(nullable = false)
	private double imdbRating;

	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "movie_category", nullable = true)
	private Category category;

	@ManyToMany(mappedBy = "inCart", fetch = FetchType.LAZY)
	@JsonIgnoreProperties("inCart")
	private List<Cart> carts;

	@ManyToMany(mappedBy = "moviesPurchesed", fetch = FetchType.LAZY)
	@JsonIgnoreProperties("moviesPurchesed")
	private List<StreamPurcheses> purcheses;

	public Category getCategory() {
		return category;
	}

	public String getNameCategory() {
		return category.getCategoryName();
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	// @Column(nullable = false)
	private LocalDate releaseDate;

	// @Column(nullable = false)
	private LocalDate addToMoviesDate;

	public LocalDate getAddToMoviesDate() {
		return addToMoviesDate;
	}

	public void setAddToMoviesDate(LocalDate addToMoviesDate) {
		this.addToMoviesDate = addToMoviesDate;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public int getDownloads() {
		return downloads;
	}

	public void setDownloads(int downloads) {
		this.downloads = downloads;
	}
	
	
	public void increaceDownloads() {
		this.downloads += 1 ;
		
	}

	public List<Cart> getCarts() {
		return carts;
	}


	public List<StreamPurcheses> getPurcheses() {
		return purcheses;
	}


	public void EmptyCarts() {
		this.carts = null ;
	}
	
}
