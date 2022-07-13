package com.binar.teekmustbe.service.product;

import com.binar.teekmustbe.dto.ProductDto;
import com.binar.teekmustbe.entitiy.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    void save(ProductDto productDto);

    List<ProductDto> findAll();

    Optional<ProductDto> findByName(String name);

    List<ProductDto> findByCategory(String category);

    boolean delete(long id);

    boolean update(ProductDto productDto);

    Optional<ProductDto> findById(long id);

    Product findProductById(long id);

}
