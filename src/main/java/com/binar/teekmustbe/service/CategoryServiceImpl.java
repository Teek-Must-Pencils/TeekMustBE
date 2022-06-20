package com.binar.teekmustbe.service;


import com.binar.teekmustbe.entitiy.Category;

import com.binar.teekmustbe.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Optional<Category> findByCategories(Category category) {
        return categoryRepository.findByCategory(category);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public void save(Category category) {
        categoryRepository.save(category);
    }
}
