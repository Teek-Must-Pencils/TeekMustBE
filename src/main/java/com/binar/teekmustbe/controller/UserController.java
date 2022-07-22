package com.binar.teekmustbe.controller;

import com.binar.teekmustbe.dto.UserDto;
import com.binar.teekmustbe.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
@SecurityRequirement(name = "Authorization")
@CrossOrigin(origins = {"http://localhost:3000", "https://pencil-store-by-teekmust.herokuapp.com/","https://teek-must-store.vercel.app/"}, maxAge = 3600)
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "Find user by id")
    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable("id") long id) {
        var user = userService.findById(id);
        if (user.isEmpty()) {
            return new ResponseEntity<>("User with username " + id + " not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new UserDto(user.get()), HttpStatus.OK);
    }

    @Operation(summary = "Find user by email")
    @GetMapping("email/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable("email") String email) {
        var user = userService.findByEmail(email);
        if (user.isEmpty()) {
            return new ResponseEntity<>("User with username " + email + " not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new UserDto(user.get()), HttpStatus.OK);
    }

    @Operation(summary = "Find user by username")
    @GetMapping("username/{username}")
    public ResponseEntity<?> findByUsername(@PathVariable("username") String username) {
        var user = userService.findByUsername(username);
        if (user.isEmpty()) {
            return new ResponseEntity<>("User with username " + username + " not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new UserDto(user.get()), HttpStatus.OK);
    }

    @Operation(summary = "List user")
    @GetMapping("")
    public ResponseEntity<?> listUsers() {
        var users = userService.findAll();
        return new ResponseEntity<>(users.stream().map(UserDto::new).collect(Collectors.toList()), HttpStatus.OK);
    }

}
