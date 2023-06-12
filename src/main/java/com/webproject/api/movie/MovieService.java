package com.webproject.api.movie;

import java.util.List;

public interface MovieService {

    MovieDto publishMovie(MovieDto movie) throws Exception;

    MovieDto getMovieDetail(String movieId);

    List<MovieDto> getMovies();

    List<MovieDto> findByTitle(int page, int limit, String sortBy, String title);

    List<MovieDto> findByCategory(String category);

    String deleteMovieDetail(String movieId) throws Exception;

    List<MovieDto> getLatestMovies();

    MovieDto updateMovie(MovieDto movieCopy, String id) throws Exception;

    List<MovieDto> getMostDownloadMovies();

    List<MovieDto> getNewLyAddedMovies();


}
