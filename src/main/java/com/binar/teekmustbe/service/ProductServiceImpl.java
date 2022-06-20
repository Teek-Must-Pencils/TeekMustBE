package com.binar.teekmustbe.service;

import com.binar.teekmustbe.dto.ProductDto;
import com.binar.teekmustbe.entitiy.Product;
import com.binar.teekmustbe.enums.Categories;
import com.binar.teekmustbe.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryService categoryService;

    public void save(ProductDto productDto) {
        var product = new Product(productDto);
        if (productDto.getCategory().isEmpty()) {
            product.getCategory().add(
                    categoryService.findByCategory(Categories.COLOR_PENCIL_8).orElseThrow(() ->
                            new RuntimeException("Error: No role Buyer Found"))
            );
        }
    }
}
