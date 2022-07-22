package com.binar.teekmustbe.service.product;

import com.binar.teekmustbe.dto.ProductDto;
import com.binar.teekmustbe.entitiy.Product;
import com.binar.teekmustbe.enums.Categories;
import com.binar.teekmustbe.repository.ProductRepository;
import com.binar.teekmustbe.service.category.CategoryService;
import com.binar.teekmustbe.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.EnumUtils.getEnumIgnoreCase;

@Service
public class ProductServiceImpl implements ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;


    public void save(ProductDto productDto) {
        var seller = userService.findByUsername(productDto.getSeller());
        assert seller.isPresent();
        var product = new Product(productDto, seller.get());
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
            var product = findById(productDto.getId()).get();

            product.setName(productDto.getName() != null ?
                    productDto.getName() : product.getName());
            if (productDto.getCategories() != null) {
                productDto.getCategories().forEach(category -> product.getCategory().add(
                        categoryService.findByCategory(getEnumIgnoreCase(Categories.class, category)).orElseThrow(() ->
                                new RuntimeException("Error: No category '" + category + "' Found. Use `Pencil 2B` as default."))));
            }
            product.setImg(productDto.getImg() != null ?
                    productDto.getImg() : product.getImg());
            product.setPrice(productDto.getPrice() != null ?
                    productDto.getPrice() : product.getPrice());
            product.setDescription(productDto.getDescription() != null ?
                    productDto.getDescription() : product.getDescription());
            product.setCity(productDto.getCity() != null ?
                    productDto.getCity() : product.getCity());
            productRepository.save(product);
            return true;
        }
        return false;
    }


    public Optional<Product> findById(long id) {
        if (productRepository.findById(id).isPresent()) {
            return productRepository.findById(id);
        }
        return Optional.empty();
    }

    public List<Product> findAll() {
        return productRepository.findAllByOrderByIdAsc();
    }


    public List<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    public List<Product> findByCategory(String category) {
        var category_ = categoryService.findByCategory(Categories.valueOf(category.toUpperCase()));
        if (category_.isEmpty()) {
            throw new RuntimeException();
        }
        return productRepository.findByCategory(category_.get());
    }

    public boolean delete(long id) {
        if (productRepository.findById(id).isPresent()) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
