package com.binar.teekmustbe.controller;

import com.binar.teekmustbe.config.JwtUtil;
import com.binar.teekmustbe.dto.JwtTokenDto;
import com.binar.teekmustbe.dto.UserDto;
import com.binar.teekmustbe.dto.UserLoginDto;
import com.binar.teekmustbe.dto.UserSignupDto;
import com.binar.teekmustbe.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@CrossOrigin(origins = {"http://localhost:3000", "https://pencil-store-by-teekmust.herokuapp.com/"}, maxAge = 3600)
public class SignUpLoginController {
    private static final Logger logger = LoggerFactory.getLogger(SignUpLoginController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;


    @Operation(summary = "Registers a new user")
    @PostMapping(value = "/signup", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> signUp(UserSignupDto userSignupDto, @ModelAttribute MultipartFile img) {
        var response = new HashMap<String, String>();
        if (userService.existsUsername(userSignupDto.getUsername())) {
            response.put(userSignupDto.getUsername(), "Error: Username already used");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else if (userService.existsEmail(userSignupDto.getEmail())) {
            response.put(userSignupDto.getUsername(), "Error: Email already used");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        logger.info(userSignupDto.toString());
        userSignupDto.setPassword(passwordEncoder.encode(userSignupDto.getPassword()));
        userSignupDto.setImg(img);
        userService.save(userSignupDto);
        return new ResponseEntity<>("User Registered", HttpStatus.CREATED);
    }

    @Operation(summary = "Login user")
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginDto credential) {
        logger.info("logging in");
        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credential.getUsername(), credential.getPassword()));
        if (!authentication.isAuthenticated()) {
            return new ResponseEntity<>("User or password incorrect", HttpStatus.FORBIDDEN);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var token = jwtUtil.generateAccessToken(authentication);
        var userDetails = (UserDetails) authentication.getPrincipal();
        var user = userService.findByUsername(userDetails.getUsername());
        if (user.isEmpty()) {
            return new ResponseEntity<>("User or password incorrect", HttpStatus.FORBIDDEN);
        }
        return ResponseEntity.ok(new JwtTokenDto(token, new UserDto(user.get())));
    }
}
