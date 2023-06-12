package com.webproject.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.webproject.api.category.Category;
import com.webproject.api.movie.Movie;


@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    Movie findByMovieId(String movieId);

    @Query("select s from Movie s where s.title like %:title%")
    List<Movie> findByTitle(@Param("title") String title);

    List<Movie> findByCategory(Category category);

    Movie getByMovieId(String movieId);
}