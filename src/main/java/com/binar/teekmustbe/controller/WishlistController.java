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

import javax.validation.Valid;

@RestController
@RequestMapping("/api/wishlist")
@SecurityRequirement(name = "Authorization")
@CrossOrigin(origins = {"http://localhost:3000", "https://pencil-store-by-teekmust.herokuapp.com/","https://teek-must-store.vercel.app/"}, maxAge = 3600)
public class WishlistController {
    private static final Logger logger = LoggerFactory.getLogger(WishlistController.class);
    @Autowired
    private WishlistService wishlistService;

    @Operation(summary = "Add new wishlist (JSON)")
    @PostMapping(value = "")
    public ResponseEntity<?> addProduct(WishlistDto wishlistDto) {
        wishlistService.save(wishlistDto);
        return new ResponseEntity<>("Wishlist add", HttpStatus.CREATED);
    }

    @Operation(summary = "List Wishlist")
    @GetMapping("")
    public ResponseEntity<?> listOffer() {
        var wishlist = wishlistService.findAll();
        return new ResponseEntity<>(wishlist, HttpStatus.OK);
    }

    @Operation(summary = "Find Wishlist")
    @GetMapping("{id}")
    public ResponseEntity<?> findWishlistById(@Valid @PathVariable("id") long id) {
        var wishlist = wishlistService.findById(id);
        if (wishlist.isEmpty()) {
            return new ResponseEntity<>("Wishlist id" + id + " not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new WishlistDto(wishlist.get()), HttpStatus.OK);
    }
}
