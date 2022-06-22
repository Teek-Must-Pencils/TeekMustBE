package com.binar.teekmustbe.controller;

import com.binar.teekmustbe.entitiy.User;
import com.binar.teekmustbe.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@SecurityRequirement(name = "Authorization")
@CrossOrigin(origins = {"http://localhost:3000", "https://pencil-store-by-teemust.herokuapp.com"}, maxAge = 3600)
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Find user by id")
    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable("id") long id) {
        var user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(summary = "Find user by username")
    @GetMapping("{userId}")
    public ResponseEntity<?> email(@PathVariable("email") String email) {
        var user = userService.findByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(summary = "Find user by email")
    @GetMapping("{username}")
    public ResponseEntity<?> findByEmail(@PathVariable("username") String username) {
        var user = userService.findByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(summary = "List user")
    @GetMapping("users")
    public ResponseEntity<?> listUsers() {
        var users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
