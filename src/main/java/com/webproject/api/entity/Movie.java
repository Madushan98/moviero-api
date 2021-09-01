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

	// @Column(nullable=false, length=50)
	private String movieUrl;

	@Column(nullable = false, length = 50)
	private String title;

	@Column(columnDefinition = "TEXT",nullable=false,length = 4000)
	private String description;

	@Column(nullable = false)
	private Double moviePrice;
	

	 @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "movie_category", nullable = false )
	    private Category category;
	
	 
	@Column(nullable = false) 
	private double imdbRating ;
	 
	 
	 
	public Category getCategory() {
		return category;
	}
	
	public String getNameCategory() {
		return category.getCategoryName() ;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	//@Column(nullable = false)
	private LocalDate releaseDate ;
	
	//@Column(nullable = false)
	private LocalDate addToMoviesDate ;
	
	

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

	@ManyToMany(mappedBy = "inCart" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	 @JsonIgnoreProperties("inCart")
	 private List<Cart> carts ; 

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
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

