package com.binar.teekmustbe.repository;

import com.binar.teekmustbe.entitiy.Category;
import com.binar.teekmustbe.enums.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCategory(Categories category);
}
