package com.binar.teekmustbe.controller;


import com.binar.teekmustbe.dto.ProductDto;
import com.binar.teekmustbe.service.product.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/product")
@SecurityRequirement(name = "Authorization")
@CrossOrigin(origins = {"http://localhost:3000", "https://pencil-store-by-teekmust.herokuapp.com/", "https://teek-must-store.vercel.app/"}, maxAge = 3600)
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductService productService;

    @Operation(summary = "Add new product (JSON)")
    @PostMapping(value = "")
    public ResponseEntity<?> saveProduct(ProductDto productDto) {
        productService.save(productDto);
        return new ResponseEntity<>("Product add", HttpStatus.CREATED);
    }

    @Operation(summary = "Find product by name")
    @GetMapping("name/{name}")
    public ResponseEntity<?> findByProductName(@PathVariable("name") String name) {
        var products = productService.findByName(name);
        return new ResponseEntity<>(products.stream().map(ProductDto::new).collect(Collectors.toList()), HttpStatus.OK);
    }

    @Operation(summary = "List Product")
    @GetMapping("")
    public ResponseEntity<?> listProducts() {
        var products = productService.findAll();
        return new ResponseEntity<>(products.stream().map(ProductDto::new).collect(Collectors.toList()), HttpStatus.OK);
    }

    @Operation(summary = "Delete Product")
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@Valid @PathVariable("id") long id) {
        var status = productService.delete(id);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @Operation(summary = "find Product")
    @GetMapping("{id}")
    public ResponseEntity<?> findProductById(@Valid @PathVariable("id") long id) {
        var status = productService.findById(id);
        if (status.isEmpty()) {
            return new ResponseEntity<>("Product id" + id + " not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ProductDto(status.get()), HttpStatus.OK);
    }


    @Operation(summary = "Find by category")
    @GetMapping("category/{category}")
    public ResponseEntity<?> findByCategory(@Valid @PathVariable("category") String productCategory) {
        var products = productService.findByCategory(productCategory);
        return new ResponseEntity<>(products.stream().map(ProductDto::new).collect(Collectors.toList()), HttpStatus.OK);
    }

    @Operation(summary = "Update product (JSON)")
    @PutMapping(value = "")
    public ResponseEntity<?> update(ProductDto productDto) {
        if (productService.update(productDto)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>("Product id not found Found", HttpStatus.NOT_FOUND);
    }
}
