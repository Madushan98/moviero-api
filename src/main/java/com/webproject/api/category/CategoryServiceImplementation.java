package com.webproject.api.category;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.webproject.api.repository.CategoryRepository;


@Service
public class CategoryServiceImplementation implements CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository ;

	@Override
	public CategoryDetailResponse save(Category category) {

		CategoryDetailResponse  categoryResponse  = new CategoryDetailResponse();
		
		Category newCategory = categoryRepository.save(category);
		
		
		BeanUtils.copyProperties(newCategory,categoryResponse);
		
		return categoryResponse;
	}

	@Override
	public CategoryDetailResponse deleteByCategoryName(String name) {
		
		CategoryDetailResponse  categoryResponse  = new CategoryDetailResponse();
		
		Category deleteCategory = categoryRepository.getByCategoryName(name);
		
		if(deleteCategory == null) {
			throw new UsernameNotFoundException(name);
		}
		
		categoryRepository.delete(deleteCategory);
		
		
		BeanUtils.copyProperties(deleteCategory,categoryResponse);
		
		return categoryResponse;
	}

	@Override
	public List<CategoryDetailResponse> findAll() {
	
		List<CategoryDetailResponse>  categoryResponseList  = new ArrayList<>();
		List<Category> allCategories = categoryRepository.findAll();
		
		for(Category category : allCategories) {
			
			CategoryDetailResponse  categoryResponse  = new CategoryDetailResponse();
			
			BeanUtils.copyProperties(category,categoryResponse );
			
			categoryResponse.setMovieCount(category.getMovieCount());
			
			categoryResponseList.add(categoryResponse);
			
		}
		
		return categoryResponseList ;
	}

	
	
}
