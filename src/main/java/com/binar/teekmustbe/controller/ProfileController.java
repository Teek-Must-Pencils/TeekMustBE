package com.binar.teekmustbe.controller;

import com.binar.teekmustbe.dto.ProductDto;
import com.binar.teekmustbe.dto.ProfileDto;
import com.binar.teekmustbe.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class ProfileController {
    @Autowired
    private UserService userService;

    @Operation(summary = "Update profile")
    @PutMapping(value = "/signup", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> update(@Valid ProfileDto profileDto, @ModelAttribute MultipartFile img) {
        profileDto.setImg(img);
        if (userService.update(profileDto)){
            return new ResponseEntity<>("Id Not Found", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

