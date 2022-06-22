package com.binar.teekmustbe.service;

import com.binar.teekmustbe.dto.ProductDto;
import com.binar.teekmustbe.dto.UserSignupDto;
import com.binar.teekmustbe.entitiy.Product;
import com.binar.teekmustbe.entitiy.User;
import com.binar.teekmustbe.enums.Categories;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    void save(ProductDto productDto);

    List<Product> findAll();

    List<Product> findByProductName(String productName);

    List<Product> findByCategory(Categories category);

    boolean delete(long id);

    boolean update(ProductDto productDto);
}
