package com.binar.teekmustbe.controller;

import com.binar.teekmustbe.dto.ProfileDto;
import com.binar.teekmustbe.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/profile")
@SecurityRequirement(name = "Authorization")
@CrossOrigin(origins = {"http://localhost:3000", "https://pencil-store-by-teekmust.herokuapp.com/"}, maxAge = 3600)
public class UserProfileController {
    @Autowired
    private UserService userService;

    @Operation(summary = "Update profile")
    @PutMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> update(ProfileDto profileDto, @ModelAttribute MultipartFile img) {
        profileDto.setImg(img);
        if (userService.update(profileDto)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>("Id Not Found", HttpStatus.NOT_FOUND);
    }
}

