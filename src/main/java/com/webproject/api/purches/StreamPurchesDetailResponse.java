package com.webproject.api.purches;

import java.util.ArrayList;
import java.util.List;

public class StreamPurchesDetailResponse {

    private String userId;

    private List<StreamMovieResponse> purchesMovies;


    public StreamPurchesDetailResponse() {
        this.purchesMovies = new ArrayList<StreamMovieResponse>();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<StreamMovieResponse> getPurchesMovies() {
        return purchesMovies;
    }

    public void setPurchesMovies(List<StreamMovieResponse> purchesMovies) {
        this.purchesMovies = purchesMovies;
    }

    public void addToPurches(StreamMovieResponse movie) {
        purchesMovies.add(movie);
    }

}
