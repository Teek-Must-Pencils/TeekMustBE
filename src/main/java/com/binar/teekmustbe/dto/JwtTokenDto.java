package com.binar.teekmustbe.dto;

import com.binar.teekmustbe.entitiy.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtTokenDto {
    private String accessToken;
    private String username;
    private String email;
    private String role;

    public JwtTokenDto(String accessToken, User user) {
        this.accessToken = accessToken;
        username = user.getUsername();
        email = user.getEmail();
        role =  user.getRoles().toString();
    }

    public JwtTokenDto() {

    }
}
