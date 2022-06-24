package com.binar.teekmustbe;

import com.binar.teekmustbe.controller.ProductController;
import com.binar.teekmustbe.dto.ProductDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class ProductTest {
    private static final Logger logger = LoggerFactory.getLogger(SignUpLoginTest.class);

    @Autowired
    private ProductController productController;

    @Test
    public void addProduct() throws IOException {
        var img = new MockMultipartFile("test_profile_photo",
                "test_profile_photo.jpg",
                "application/octet-stream",
                new ClassPathResource("img/test_profile_photo.jpg")
                        .getInputStream());
        var product = new ProductDto()
                .setId(0)
                .setCategories(Set.of("pencil_2b"))
                .setCity("test city")
                .setDescription("Lorem ipsum")
                .setPrice(BigDecimal.valueOf(34234))
                .setName("Top Pencil")
                .setSeller("Clerk")
                .setImg(img);
        productController.addProduct(product, product.getImg());
        var response = productController.findByProductName("Top Pencil");
        assertEquals(product.setImg(null),
                ((List<ProductDto>) response.getBody()).get(0)
                        .setId(0)
                        .setImgB(null)
                        .setCategories(Set.of("pencil_2b")));
    }

}
