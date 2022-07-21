package com.binar.teekmustbe.controller;


import com.binar.teekmustbe.dto.OfferDto;
import com.binar.teekmustbe.dto.ProductDto;
import com.binar.teekmustbe.dto.ProfileDto;
import com.binar.teekmustbe.entitiy.Product;
import com.binar.teekmustbe.entitiy.User;
import com.binar.teekmustbe.service.offer.OfferService;
import com.binar.teekmustbe.service.user.UserService;
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
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/offer")
@SecurityRequirement(name = "Authorization")
@CrossOrigin(origins = {"http://localhost:3000", "https://pencil-store-by-teekmust.herokuapp.com/","https://teek-must-store.vercel.app/"}, maxAge = 3600)
public class OfferController {
    private static final Logger logger = LoggerFactory.getLogger(OfferController.class);
    @Autowired
    private OfferService offerService;

    @Autowired
    private UserService userService;


    @Operation(summary = "Add new offer")
    @PostMapping(value = "/save")
    public ResponseEntity<?> saveOffer(OfferDto offerDto) {
        offerService.save(offerDto);
        return new ResponseEntity<>("Offer add", HttpStatus.CREATED);
    }

    @Operation(summary = "List Offer")
    @GetMapping("")
    public ResponseEntity<?> listOffer() {
        var offer = offerService.findAll();
        return new ResponseEntity<>(offer.stream().map(OfferDto::new).collect(Collectors.toList()), HttpStatus.OK);
    }

    @Operation(summary = "Update offer")
    @PutMapping(value = "/update")
    public ResponseEntity<?> update(OfferDto offerDto) {
        if (offerService.update(offerDto)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>("Offer Not Found", HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Find Offer")
    @GetMapping("Offer/{id}")
    public ResponseEntity<?> findOfferById(@Valid @PathVariable("id") long id) {
        var offer = offerService.findById(id);
        if (offer.isEmpty()) {
            return new ResponseEntity<>("Offer id" + id + " not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new OfferDto(offer.get()), HttpStatus.OK);
    }
}
