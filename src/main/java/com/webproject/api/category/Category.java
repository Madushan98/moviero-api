package com.webproject.api.category;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.webproject.api.movie.Movie;


@Entity
public class Category {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false)
	private String categoryName ;

	@OneToMany(mappedBy = "category", cascade = CascadeType.DETACH, orphanRemoval = true)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Movie> movies;
	
	public Category() {
		this.movies = new ArrayList<>();
	}
	
	public Category(String categoryName,String categoryUrlPath,String categoryImageUrl) {
		this.categoryName = categoryName ;
		this.movies = Collections.emptyList();
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public void addMovies(Movie movie) {
		movies.add(movie);
	}

	public int getMovieCount() {
		
		int movieCount = this.movies.size();
		
		return movieCount;
	}

}

