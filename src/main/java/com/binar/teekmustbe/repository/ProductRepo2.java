package com.binar.teekmustbe.repository;

import com.binar.teekmustbe.entitiy.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo2 extends JpaRepository<Product,Long> {
    public List<Product> findByName(String nameProduct);
}
