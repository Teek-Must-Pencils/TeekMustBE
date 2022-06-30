package com.binar.teekmustbe.service;

import com.binar.teekmustbe.dto.ProductDto;
import com.binar.teekmustbe.enums.Categories;

import java.util.List;

public interface ProductService {
    void save(ProductDto productDto);

    List<ProductDto> findAll();

    List<ProductDto> findByName(String name);

    List<ProductDto> findByCategory(String category);

    boolean delete(long id);

    boolean update(ProductDto productDto);
}
