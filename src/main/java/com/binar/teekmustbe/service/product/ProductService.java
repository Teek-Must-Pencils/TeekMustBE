package com.binar.teekmustbe.service.product;

import com.binar.teekmustbe.dto.ProductDto;
import com.binar.teekmustbe.entitiy.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    void save(ProductDto productDto);

    List<Product> findAll();

    List<Product> findByName(String name);

    List<Product> findByCategory(String category);

    boolean delete(long id);

    boolean update(ProductDto productDto);

    Optional<Product> findById(long id);
}
