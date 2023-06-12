package com.webproject.api.purches;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.webproject.api.movie.Movie;
import com.webproject.api.user.UserModel;

@Entity
public class StreamPurcheses {

    private static final long serialVersionUID = -4652777307512175027L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne(mappedBy = "purcheses")
    @JsonIgnoreProperties(value = {"purchese", "handler", "hibernateLazyInitializer"}, allowSetters = true)
    private UserModel user;

    @ManyToMany()
    @JoinTable(name = "movie_purchese", joinColumns = @JoinColumn(name = "movieId"), inverseJoinColumns = @JoinColumn(name = "purcheseId"))
    private List<Movie> moviesPurchesed;

    public StreamPurcheses() {
        moviesPurchesed = new ArrayList<Movie>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addToPurches(Movie movie) {
        moviesPurchesed.add(movie);
    }

    public List<Movie> getPurches() {
        return moviesPurchesed;
    }

    public Boolean isMovieInPurchese(String movieId) {
        for (Movie movie : this.moviesPurchesed) {
            String id = movie.getMovieId();
            if (id.equals(movieId)) {
                return true;
            }
        }
        return false;
    }

    public void removefromPurchese(String movieId) {
        List<Movie> newPurches = new ArrayList<>();
        for (Movie movie : this.moviesPurchesed) {
            String id = movie.getMovieId();
            if (!id.equals(movieId)) {
                newPurches.add(movie);
            }
        }
        this.moviesPurchesed = newPurches;

    }

}
