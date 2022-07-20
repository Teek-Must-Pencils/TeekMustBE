package com.binar.teekmustbe.controller;

import com.binar.teekmustbe.dto.OfferDto;
import com.binar.teekmustbe.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/notification")
@SecurityRequirement(name = "Authorization")
@CrossOrigin(origins = {"http://localhost:3000", "https://pencil-store-by-teekmust.herokuapp.com/","https://teek-must-store.vercel.app/"}, maxAge = 3600)
public class NotificationController {
    private static final Logger logger = LoggerFactory.getLogger(OfferController.class);

    @Autowired
    private NotificationService notificationService;

    @Operation(summary = "List Notificiaton")
    @GetMapping("/buyer")
    public ResponseEntity<?> listNotificationSeller() {

        var notification = notificationService.notificationBuyer(authentication().getName());
        return new ResponseEntity<>(notification, HttpStatus.OK);
    }

    @Operation(summary = "List Notificiaton")
    @GetMapping("/seller")
    public ResponseEntity<?> listNotificationBuyer() {
        var notification = notificationService.notificationSeller(authentication().getName());
        return new ResponseEntity<>(notification, HttpStatus.OK);
    }

    public Authentication authentication(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth;
    }

}
