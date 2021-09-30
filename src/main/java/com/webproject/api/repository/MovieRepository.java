package com.webproject.api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.webproject.api.entity.Category;
import com.webproject.api.entity.Movie;


@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

	Movie findByMovieId(String movieId);
	

	
	@Query("select s from Movie s where title like %?1%")
	List<Movie> findByTitle(String title);
	
	List<Movie> findByCategory(Category category);



	Movie getByMovieId(String movieId);
	
	
}