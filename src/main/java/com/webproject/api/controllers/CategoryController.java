package com.webproject.api.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webproject.api.entity.*;
import com.webproject.api.repository.*;
import com.webproject.api.userLayer.*;
import com.webproject.api.movieLayer.*;

@RestController
@RequestMapping("categories")
public class CategoryController {
	
	@Autowired
	CategoryRepository categoryRepository ;
	
	@Autowired
	MovieService movieService ;
	

	@PostMapping
	public Category createCategory(@RequestBody String categoryName) {
		
		Category newCategory = new Category(categoryName);
		
		Category returnCategory = categoryRepository.save(newCategory);
		
		
		
		
		return returnCategory ;
	}
	
	@DeleteMapping(path="/{name}")
	public String deleteCategory(@PathVariable String name){
		
		
		
		Category deleteCategory = categoryRepository.getCategoryByCategoryName(name) ;
		
		if(deleteCategory == null) {
			throw new UsernameNotFoundException(name);
		}
		
		categoryRepository.delete(deleteCategory);
		
		return "User Deleted SuccesFully";
		
	}
	
	
	@GetMapping
	public List<Category> getAllCategories() {
	
		List<Category> allCategories = categoryRepository.findAll();

		return allCategories ;
	}
	
	@GetMapping(path="/{category}")
	public List<MovieDetailsResponse> getMovies(@PathVariable String category){
		
		Category retriveCategory = categoryRepository.getCategoryByCategoryName(category) ;
		
		
		
		
	
		
		return null;
		
		
		
	}
	
	
}
