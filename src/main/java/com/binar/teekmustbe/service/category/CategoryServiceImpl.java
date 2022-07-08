package com.binar.teekmustbe.service.category;


import com.binar.teekmustbe.dto.CategoryDto;
import com.binar.teekmustbe.entitiy.Category;

import com.binar.teekmustbe.enums.Categories;
import com.binar.teekmustbe.repository.CategoryRepository;
import com.binar.teekmustbe.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Optional<Category> findByCategory(Categories category) {
//        if (categoryRepository.findByCategory(category).isPresent()) {
//            return Optional.of(new CategoryDto(categoryRepository.findByCategory(category).get()));
//        }
//        return Optional.empty();
        return categoryRepository.findByCategory(category);
    }

    public Set<CategoryDto> findAll() {
        return categoryRepository.findAll().stream().map(CategoryDto::new).collect(Collectors.toSet());
    }

    public void save(Category category) {
        categoryRepository.save(category);
    }
}
