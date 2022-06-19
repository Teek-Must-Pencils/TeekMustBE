package com.binar.teekmustbe.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
public class UserByIdDto {

    private String username;
    private String email;
    private Set<String> roles = new HashSet<>();
}
