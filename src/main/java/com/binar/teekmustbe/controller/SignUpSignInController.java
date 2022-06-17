package com.binar.teekmustbe.controller;

import com.binar.teekmustbe.config.JwtUtil;
import com.binar.teekmustbe.dto.JwtTokenDto;
import com.binar.teekmustbe.dto.UserLoginDto;
import com.binar.teekmustbe.dto.UserSignupDto;
import com.binar.teekmustbe.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("/api/auth")
@SecurityRequirement(name = "Authorization")
public class SignUpSignInController {
    private static final Logger logger = LoggerFactory.getLogger(SignUpSignInController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;



    @Operation(summary = "Registers a new user")
    @PostMapping(value = "/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody UserSignupDto userSignupDto, @RequestParam("img") MultipartFile img) {
        var response = new HashMap<String, String>();
        if (userService.existsUsername(userSignupDto.getUsername())) {
            response.put(userSignupDto.getUsername(), "Error: Username already used");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else if (userService.existsEmail(userSignupDto.getEmail())) {
            response.put(userSignupDto.getUsername(), "Error: Email already used");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        userSignupDto.setPassword(passwordEncoder.encode(userSignupDto.getPassword()));
        userSignupDto.setImg(img);
        userService.save(userSignupDto);
        return new ResponseEntity<>("User Registered", HttpStatus.CREATED);    }

    @Operation(summary = "Login user")
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginDto credential) {
        logger.info("logging in");
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(credential.getUsername(), credential.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        var token = jwtUtil.generateAccessToken(authentication);
        var userDetails = (UserDetails) authentication.getPrincipal();
        logger.info("User " + userDetails.getUsername() + " logged in.");
        logger.info(token);
        return ResponseEntity.ok(new JwtTokenDto(token));
    }
}
