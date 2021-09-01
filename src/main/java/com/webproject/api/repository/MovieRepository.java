package com.webproject.api.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.webproject.api.entity.Movie;


@Repository
public interface MovieRepository extends PagingAndSortingRepository<Movie, Long> {

	Movie findByMovieId(String movieId);

}