package com.webproject.api.controllers;


import java.util.List;

import com.webproject.api.category.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webproject.api.category.CategoryDetailResponse;
import com.webproject.api.category.CategoryService;
import com.webproject.api.movie.*;

@RestController
@RequestMapping("categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    MovieService movieService;


    @PostMapping
    public CategoryDetailResponse createCategory(@RequestBody Category category) {

        CategoryDetailResponse returnCategory = categoryService.save(category);

        return returnCategory;
    }

    @DeleteMapping(path = "/{name}")
    public String deleteCategory(@PathVariable String name) {


        CategoryDetailResponse deleteCategory = categoryService.deleteByCategoryName(name);


        return "User Deleted SuccesFully";

    }


    @GetMapping
    public List<CategoryDetailResponse> getAllCategories() {

        List<CategoryDetailResponse> allCategories = categoryService.findAll();


        return allCategories;
    }


}
