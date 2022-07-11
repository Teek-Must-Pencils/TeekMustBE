package com.binar.teekmustbe.controller;

import com.binar.teekmustbe.dto.WishlistDto;
import com.binar.teekmustbe.service.wishlist.WishlistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wishlist")
@SecurityRequirement(name = "Authorization")
@CrossOrigin(origins = {"http://localhost:3000", "https://pencil-store-by-teekmust.herokuapp.com/"}, maxAge = 3600)
public class WishlistController {
    private static final Logger logger = LoggerFactory.getLogger(WishlistController.class);
    @Autowired
    private WishlistService wishlistService;

    @Operation(summary = "Add new wishlist")
    @PostMapping(value = "/")
    public ResponseEntity<?> addProduct(WishlistDto wishlistDto) {
        wishlistService.save(wishlistDto);
        return new ResponseEntity<>("Wish list add", HttpStatus.CREATED);
    }
}