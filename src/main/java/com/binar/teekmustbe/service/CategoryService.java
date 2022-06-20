package com.binar.teekmustbe.service;

import com.binar.teekmustbe.entitiy.Category;
import com.binar.teekmustbe.entitiy.Role;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();

    void save(Category category);
    Optional<Category> findByCategory (Category categories);

}
