package com.binar.teekmustbe.controller;

import com.binar.teekmustbe.entitiy.User;
import com.binar.teekmustbe.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Find user by id")
    @GetMapping("{userId}")
    public ResponseEntity<?> getByIdController(@PathVariable("userId") long user_id) {
        var user = userService.findById(user_id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(summary = "List user")
    @GetMapping("users")
    public ResponseEntity<?> listUsers() {
        var users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
