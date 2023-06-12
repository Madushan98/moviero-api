package com.webproject.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webproject.api.category.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>  {
	Category getByCategoryName(String categoryName);

}
