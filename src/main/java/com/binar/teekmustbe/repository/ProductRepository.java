package com.binar.teekmustbe.repository;

import com.binar.teekmustbe.entitiy.Category;
import com.binar.teekmustbe.entitiy.Product;
import com.binar.teekmustbe.enums.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByOrderByIdAsc();

    List<Product> findByCategory(Categories category);

    Optional<Product> findByName(String name);

    List<Product> findByCategory(Category category);

    Product findProductById(long id);
}
