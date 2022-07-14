package com.binar.teekmustbe.controller;


import com.binar.teekmustbe.service.offer.OfferService;
import com.binar.teekmustbe.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

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
    @PostMapping("add/{productId}/{id}")
    public ResponseEntity<?> addProduct(
//                @Schema(example = "{\n" + "  \"price\": \"250000\"\n" + "}")
//                @RequestBody Map<Integer,Object> priceNegotiated,
            @RequestParam BigDecimal priceNegotiated,
            @PathVariable("id") long id,
            @PathVariable("productId") long productId) {

        var user = userService.findById(id);
        offerService.save(productId, priceNegotiated, user.get());
        return new ResponseEntity<>("offer add", HttpStatus.CREATED);
    }
}
