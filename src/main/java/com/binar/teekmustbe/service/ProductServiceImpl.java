package com.binar.teekmustbe.service;

import com.binar.teekmustbe.dto.ProductDto;
import com.binar.teekmustbe.entitiy.Product;
import com.binar.teekmustbe.enums.Categories;
import com.binar.teekmustbe.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


import static org.apache.commons.lang3.EnumUtils.getEnumIgnoreCase;

@Service
public class ProductServiceImpl implements ProductService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryService categoryService;

    public void save(ProductDto productDto) {
        var product = new Product(productDto);
        if (productDto.getCategories().isEmpty()) {
            product.getCategory().add(
                    categoryService.findByCategory(Categories.PENCIL_2B).orElseThrow(() ->
                            new RuntimeException("Error: No category 'Pencil 2B' Found"))
            );
        } else {
            productDto.getCategories().forEach(category -> product.getCategory().add(
                    categoryService.findByCategory(getEnumIgnoreCase(Categories.class, category)).orElseThrow(() ->
                            new RuntimeException("Error: No category '" + category + "' Found. Use `Pencil 2B` as default."))
            ));
        }
        productRepository.save(product);
    }

    public boolean update(ProductDto productDto) {
        if (productRepository.findById(productDto.getId()).isPresent()) {
            productRepository.save(new Product(productDto));
            return true;
        }
        return false;
    }

    public List<ProductDto> findAll() {
        return productRepository.findAllByOrderByIdAsc().stream().map(ProductDto::new).collect(Collectors.toList());
    }

    public List<ProductDto> findByName(String name) {
        return productRepository.findByName(name).stream().map(ProductDto::new).collect(Collectors.toList());
    }

    public List<ProductDto> findByCategory(Categories category) {
        return productRepository.findByCategory(category).stream().map(ProductDto::new).collect(Collectors.toList());
    }

    public boolean delete(long id) {
        if (productRepository.findById(id).isPresent()) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
