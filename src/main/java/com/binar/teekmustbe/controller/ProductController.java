package com.binar.teekmustbe.controller;

import com.binar.teekmustbe.dto.ProductDto;
import com.binar.teekmustbe.entitiy.Product;
import com.binar.teekmustbe.repository.ProductRepository;
import com.binar.teekmustbe.service.product.ProductService;
import com.binar.teekmustbe.service.product.ProductServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/product")
@SecurityRequirement(name = "Authorization")
@CrossOrigin(origins = {"http://localhost:3000", "https://pencil-store-by-teekmust.herokuapp.com/"}, maxAge = 3600)
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductService productService;
    @Autowired
    ProductServices productServices;

    @Operation(summary = "Add new product")
    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addProduct(ProductDto productDto, @ModelAttribute MultipartFile img) {
        productDto.setImg(img);
        productService.save(productDto);
        return new ResponseEntity<>("Product add", HttpStatus.CREATED);
    }

    @Operation(summary = "Find product by name")
    @GetMapping("/{productName}")
    public ResponseEntity<?> findByProductName(@Valid @PathVariable("productName") String name) {
        List<Product> product = productServices.findByNameNew(name);
        System.out.println(product);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @Operation(summary = "List Product")
    @GetMapping("products")
    public ResponseEntity<?> listProducts() {
        var products = productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Operation(summary = "Delete Product")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@Valid @PathVariable("id") long id) {
        var product = productService.delete(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

//    @Operation(summary = "Find by category")
//    @GetMapping("{category}")
//    public ResponseEntity<?> findByCategory(@Valid @PathVariable("category") String productCategory) {
//        var category = productService.findByCategory(productCategory);
//        return new ResponseEntity<>(category, HttpStatus.OK);
//    }
}
