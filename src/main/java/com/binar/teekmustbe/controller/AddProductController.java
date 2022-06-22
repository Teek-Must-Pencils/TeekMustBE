package com.binar.teekmustbe.controller;

import com.binar.teekmustbe.dto.ProductDto;
import com.binar.teekmustbe.dto.UserSignupDto;
import com.binar.teekmustbe.service.ProductService;
import com.binar.teekmustbe.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

@RestController
@RequestMapping("/api/product")
@SecurityRequirement(name = "Authorization")
@CrossOrigin(origins = {"http://localhost:3000"}, maxAge = 3600)
public class AddProductController {
    @Autowired
    private ProductService productService;

    @Operation(summary = "Add new product")
    @PostMapping(value = "/product", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addProduct(ProductDto productDto, @ModelAttribute MultipartFile img) {
        productDto.setImg(img);
        productService.save(productDto);
        return new ResponseEntity<>("Product add", HttpStatus.CREATED);
    }
}
