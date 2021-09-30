package com.webproject.api.categoryLayer;

import java.util.List;

import com.webproject.api.entity.Category;

public interface CategoryService {

	CategoryDetailResponse save(Category category);



	List<CategoryDetailResponse> findAll();

	CategoryDetailResponse deleteByCategoryName(String name);

}
