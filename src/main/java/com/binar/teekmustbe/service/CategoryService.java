package com.binar.teekmustbe.service;

import com.binar.teekmustbe.dto.CategoryDto;
import com.binar.teekmustbe.entitiy.Category;
import com.binar.teekmustbe.enums.Categories;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CategoryService {
    Set<CategoryDto> findAll();

    void save(Category category);

    Optional<Category> findByCategory(Categories category);



}
