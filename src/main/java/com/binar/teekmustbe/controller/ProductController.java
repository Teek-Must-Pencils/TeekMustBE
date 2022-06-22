package com.binar.teekmustbe.controller;

import com.binar.teekmustbe.dto.ProductDto;
import com.binar.teekmustbe.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = {"http://localhost:3000", "https://pencil-store-by-teekmust.herokuapp.com/"}, maxAge = 3600)
public class ProductController {
    @Autowired
    private ProductService productService;
    @Operation(summary = "Add new product")
    @PostMapping(value = "/product", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addProduct(ProductDto productDto, @ModelAttribute MultipartFile img) {
        productDto.setImg(img);
        productService.save(productDto);
        return new ResponseEntity<>("Product add", HttpStatus.CREATED);
    }

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
