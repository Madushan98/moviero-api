package com.webproject.api.category;

import java.util.List;

public interface CategoryService {

	CategoryDetailResponse save(Category category);



	List<CategoryDetailResponse> findAll();

	CategoryDetailResponse deleteByCategoryName(String name);

}
