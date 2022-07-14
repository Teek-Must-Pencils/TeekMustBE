package com.binar.teekmustbe.config;
import com.binar.teekmustbe.entitiy.Category;
import com.binar.teekmustbe.enums.Categories;
import com.binar.teekmustbe.service.category.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PopulateCategoriesConfig {
    @Autowired
    private CategoryService categoryService;

    private static final Logger logger = LoggerFactory.getLogger(PopulateCategoriesConfig.class);

    @Bean
    public void populateCategories() {
        for (var category : Categories.values()) {
            var dbCategory = categoryService.findByCategory(category);
            if (!dbCategory.isPresent()) {
                logger.info("Category " + category.name() + " is not found, inserting to DB . . .");
                var category1 = new Category();
                category1.setCategory(category);
                categoryService.save(category1);
                logger.info(categoryService.findAll().toString());
            }
        }
    }

}
