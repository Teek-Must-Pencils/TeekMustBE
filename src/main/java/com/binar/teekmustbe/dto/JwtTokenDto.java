package com.binar.teekmustbe.dto;

import com.binar.teekmustbe.entitiy.Role;
import com.binar.teekmustbe.entitiy.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

@Setter
@Getter
@ToString
public class JwtTokenDto {
    private String accessToken;
    private String username;
    private String email;
    private String role;

    public JwtTokenDto(String accessToken, User user) {
        this.accessToken = accessToken;
        username = user.getUsername();
        email = user.getEmail();
        role = user.getRoles().stream().map(Role::getRole).map(Enum::name).map(String::toLowerCase).collect(Collectors.toList()).toString();

    }

    public JwtTokenDto() {

    }
}
