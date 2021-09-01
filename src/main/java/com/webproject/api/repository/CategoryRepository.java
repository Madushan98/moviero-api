package com.webproject.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webproject.api.entity.Category;




public interface CategoryRepository extends JpaRepository<Category, Long>  {

	Category getCategoryByCategoryName(String categoryName);
	
}
