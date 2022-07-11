package com.binar.teekmustbe.controller;

import com.binar.teekmustbe.dto.OfferDto;
import com.binar.teekmustbe.dto.ProductDto;
import com.binar.teekmustbe.service.offer.OfferService;
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

@RestController
@RequestMapping("/api/offer")
@SecurityRequirement(name = "Authorization")
@CrossOrigin(origins = {"http://localhost:3000", "https://pencil-store-by-teekmust.herokuapp.com/"}, maxAge = 3600)
public class OfferController {
    private static final Logger logger = LoggerFactory.getLogger(OfferController.class);
    @Autowired
    private OfferService offerService;

    @Operation(summary = "Add new offer")
    @PostMapping("add/")
    public ResponseEntity<?> addProduct(OfferDto offer) {
        offerService.save(offer);
        return new ResponseEntity<>("offer add", HttpStatus.CREATED);
    }
}
