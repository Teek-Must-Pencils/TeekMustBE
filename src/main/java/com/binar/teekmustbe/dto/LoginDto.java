package com.binar.teekmustbe.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@Accessors(chain = true)// Untuk chaining
public class LoginDto {
    @Email(message = "Email tidak valid!")
    @NotBlank(message = "Email harus diisi!")// Handling email yang tidak diisi atau kosong
    private String email;
    @NotBlank(message = "Password harus diisi!")
    private String password;
    @NotBlank(message = "Username harus diisi!")
    private String username;
}
