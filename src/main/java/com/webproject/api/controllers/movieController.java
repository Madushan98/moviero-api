package com.webproject.api.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webproject.api.repository.*;
import com.webproject.api.movie.*;


@RestController
@RequestMapping("movies")
public class movieController {

    @Autowired
    MovieService movieService;

    @Autowired
    MovieRepository movieRepository;

    @GetMapping
    public Page<MovieDetailsResponse> Getmovies(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "limit", defaultValue = "20") int limit, @RequestParam(value = "sortBy", defaultValue = "title") String sortBy) {

        List<MovieDetailsResponse> returnMovies = new ArrayList<>();

        List<MovieDto> movies = movieService.getMovies();


        for (MovieDto movieDto : movies) {
            MovieDetailsResponse movie = new MovieDetailsResponse();
            BeanUtils.copyProperties(movieDto, movie);
            returnMovies.add(movie);
        }


        Pageable pageableRequest = PageRequest.of(page, limit);

        int start = (int) pageableRequest.getOffset();

        final int end = Math.min((start + pageableRequest.getPageSize()), returnMovies.size());

        Page<MovieDetailsResponse> resultPage = new PageImpl<>(returnMovies.subList(start, end), pageableRequest, returnMovies.size());

        return resultPage;
    }


    @GetMapping("/latest")
    public List<MovieDetailsResponse> getLatestMovies() {

        List<MovieDetailsResponse> returnMovies = new ArrayList<>();

        List<MovieDto> movies = movieService.getLatestMovies();


        for (MovieDto movieDto : movies) {
            MovieDetailsResponse movie = new MovieDetailsResponse();
            BeanUtils.copyProperties(movieDto, movie);
            returnMovies.add(movie);
        }


        return returnMovies;

    }


    @GetMapping("/popular")
    public List<MovieDetailsResponse> getPopularMovies() {

        List<MovieDetailsResponse> returnMovies = new ArrayList<>();

        List<MovieDto> movies = movieService.getMostDownloadMovies();


        for (MovieDto movieDto : movies) {
            MovieDetailsResponse movie = new MovieDetailsResponse();
            BeanUtils.copyProperties(movieDto, movie);
            returnMovies.add(movie);
        }


        return returnMovies;

    }

    @GetMapping("/new")
    public List<MovieDetailsResponse> getNewMovies() {

        List<MovieDetailsResponse> returnMovies = new ArrayList<>();

        List<MovieDto> movies = movieService.getNewLyAddedMovies();


        for (MovieDto movieDto : movies) {
            MovieDetailsResponse movie = new MovieDetailsResponse();
            BeanUtils.copyProperties(movieDto, movie);
            returnMovies.add(movie);
        }


        return returnMovies;

    }


    @GetMapping("/search")
    public Page<MovieDetailsResponse> Searchmovies(
            @RequestParam(value = "title", defaultValue = "_") String title,
            @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "limit", defaultValue = "20") int limit, @RequestParam(value = "sortBy", defaultValue = "All") String sortBy) {


        List<MovieDto> movies = movieService.findByTitle(page, limit, sortBy, title);

        List<MovieDetailsResponse> returnMovies = new ArrayList<>();

        for (MovieDto movieRe : movies) {

            String movieCategory = movieRe.getMovieCategory();

            if (movieCategory.equals(sortBy) || sortBy.equals("All")) {
                MovieDetailsResponse movie = new MovieDetailsResponse();
                BeanUtils.copyProperties(movieRe, movie);
                returnMovies.add(movie);
            }

        }

        Pageable pageableRequest = PageRequest.of(page, limit);

        int start = (int) pageableRequest.getOffset();

        final int end = Math.min((start + pageableRequest.getPageSize()), returnMovies.size());

        Page<MovieDetailsResponse> resultPage = new PageImpl<>(returnMovies.subList(start, end), pageableRequest, returnMovies.size());


        return resultPage;


    }


    @GetMapping("/{category}")
    public Page<MovieDetailsResponse> getMoviesByCategory(@PathVariable(value = "category") String category,
                                                          @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "limit", defaultValue = "20") int limit, @RequestParam(value = "sortBy", defaultValue = "title") String sortBy) {

        List<MovieDetailsResponse> returnMovies = new ArrayList<>();


        if (category.equals("all")) {
            List<MovieDto> movies = movieService.getMovies();


            for (MovieDto movieRe : movies) {
                MovieDetailsResponse movie = new MovieDetailsResponse();
                BeanUtils.copyProperties(movieRe, movie);
                returnMovies.add(movie);
            }


        } else {
            List<MovieDto> movies = movieService.findByCategory(category);


            for (MovieDto movieRe : movies) {
                MovieDetailsResponse movie = new MovieDetailsResponse();
                BeanUtils.copyProperties(movieRe, movie);
                returnMovies.add(movie);
            }
        }


        Pageable pageableRequest = PageRequest.of(page, limit);

        int start = (int) pageableRequest.getOffset();

        final int end = Math.min((start + pageableRequest.getPageSize()), returnMovies.size());

        Page<MovieDetailsResponse> resultPage = new PageImpl<>(returnMovies.subList(start, end), pageableRequest, returnMovies.size());

        return resultPage;


    }


    @PostMapping
    public MovieDetailsResponse CreateMovie(@RequestBody MovieDetailRequest movieDetail) throws Exception {

        MovieDetailsResponse movieRest = new MovieDetailsResponse();

        MovieDto movieCopy = new MovieDto();
        BeanUtils.copyProperties(movieDetail, movieCopy);

        movieCopy.setAddToMoviesDate(LocalDate.now());

        MovieDto returnMovie = movieService.publishMovie(movieCopy);


        BeanUtils.copyProperties(returnMovie, movieRest);

        movieRest.setMovieCategory(returnMovie.getMovieCategory());


        return movieRest;
    }


    @GetMapping("/mov/{movieId}")
    public MovieDetailsResponse GetMovieDetails(@PathVariable(value = "movieId") String movieId) {


        MovieDetailsResponse returnMovie = new MovieDetailsResponse();


        MovieDto movieDetail = movieService.getMovieDetail(movieId);

        BeanUtils.copyProperties(movieDetail, returnMovie);


        return returnMovie;

    }


    @DeleteMapping("/mov/{movieId}")
    public String DeleteMovie(@PathVariable(value = "movieId") String movieId) throws Exception {


        String response = movieService.deleteMovieDetail(movieId);

        return response;


    }


}
