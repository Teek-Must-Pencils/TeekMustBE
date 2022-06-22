package com.binar.teekmustbe.controller;

import com.binar.teekmustbe.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Operation(summary = "Find product by name")
    @GetMapping("{productName}")
    public ResponseEntity<?> findByProductName(@PathVariable("productName") String productName) {
        var product = productService.findByProductName(productName);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @Operation(summary = "List Product")
    @GetMapping("products")
    public ResponseEntity<?> listProducts() {
        var products = productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Operation(summary = "Delete Product")
    @GetMapping("delete/{id}")
    public ResponseEntity<?>delete(@PathVariable("id")long id) {
       var product = productService.delete(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }


}
