package com.webproject.api.movie;

import java.io.Serializable;
import java.time.LocalDate;

import com.webproject.api.cart.Cart;

public class MovieDto implements Serializable {

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

    private Cart cart;

    private LocalDate releaseDate;

    private LocalDate addToMoviesDate;

    private int downloads;


    public int getDownloads() {
        return downloads;
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }

    public String getMovieCategory() {
        return movieCategory;
    }

    public void setMovieCategory(String movieCategory) {
        this.movieCategory = movieCategory;
    }

    private Double moviePrice;

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


    public Double getMoviePrice() {
        return moviePrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMoviePrice(Double moviePrice) {
        this.moviePrice = moviePrice;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
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

}
