package com.binar.teekmustbe.service.product;

import com.binar.teekmustbe.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    void save(ProductDto productDto);

    List<ProductDto> findAll();

    List<ProductDto> findByName(String name);

    List<ProductDto> findByCategory(String category);

    boolean delete(long id);

    boolean update(ProductDto productDto);

    Optional<ProductDto> findById(long id);
}
