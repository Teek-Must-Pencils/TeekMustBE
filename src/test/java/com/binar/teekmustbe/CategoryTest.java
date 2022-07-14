package com.binar.teekmustbe;

import com.binar.teekmustbe.controller.CategoryController;
import com.binar.teekmustbe.dto.CategoryDto;
import com.binar.teekmustbe.enums.Categories;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CategoryTest {
    @Autowired
    private CategoryController categoryController;

    @Test
    public void listCategories() {
        var categories = new HashSet<CategoryDto>();
        var i = 1;
        for (var category : Categories.values()) {
            categories.add(new CategoryDto().setId(i++).setCategories(category.name().toLowerCase()));
        }
        var response = categoryController.findAll();
        var responseCategories = Objects.requireNonNull(response.getBody());
        assertEquals(categories, responseCategories);
    }
}
