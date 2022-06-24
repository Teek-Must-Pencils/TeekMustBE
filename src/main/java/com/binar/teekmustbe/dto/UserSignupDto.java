package com.binar.teekmustbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@ToString
@Accessors(chain = true)// Untuk chaining
public class UserSignupDto {
    @NotBlank(message = "Username harus diisi!")
    private String username;
    @NotBlank(message = "Password harus diisi!")
    private String password;
    @Email(message = "Email tidak valid!")
    @NotBlank(message = "Email harus diisi!")// Handling email yang tidak diisi atau kosong
    private String email;
    @NotBlank(message = "Alamat harus diisi!")
    private String address;
    @NotBlank(message = "telpon harus diisi!")
    private String number;
    @JsonIgnore
    private MultipartFile img;

    private Set<String> roles = new HashSet<>();
}
