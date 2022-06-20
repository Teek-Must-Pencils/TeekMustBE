package com.binar.teekmustbe.service;

import com.binar.teekmustbe.dto.ProductDto;
import com.binar.teekmustbe.dto.UserSignupDto;
import com.binar.teekmustbe.entitiy.Product;
import com.binar.teekmustbe.entitiy.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    void save(ProductDto productDto);

    List<Product> findAll();

    Optional<User> findByProductName(long productName);
    Optional<User> findByCategory(long category);
    boolean delete(long id);


}
