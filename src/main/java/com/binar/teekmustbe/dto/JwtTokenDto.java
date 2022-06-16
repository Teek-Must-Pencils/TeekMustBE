package com.binar.teekmustbe.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtTokenDto {
    private String accessToken;

    public JwtTokenDto(String accessToken) {
        this.accessToken = accessToken;
    }

    public JwtTokenDto() {

    }
}
