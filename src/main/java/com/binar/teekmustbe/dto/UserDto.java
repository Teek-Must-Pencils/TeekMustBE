package com.binar.teekmustbe.dto;

import com.binar.teekmustbe.entitiy.Role;
import com.binar.teekmustbe.entitiy.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
import java.util.stream.Collectors;

@Setter
@Getter
@ToString
@Accessors(chain = true)// Untuk chaining
public class UserDto {
    @NotBlank
    private long id;
    @NotBlank(message = "Username harus diisi!")
    private String username;
    @JsonIgnore
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private byte[] imgB;

    private Set<String> roles = new HashSet<>();

    public UserDto() {

    }

    public UserDto(User user) {
        id = user.getId();
        username = user.getUsername();
        password = user.getPassword();
        email = user.getEmail();
        address = user.getAddress();
        number = user.getNumber();
        imgB = user.getImg();
        roles = user.getRoles().stream().map(Role::getRole).map(Enum::name).collect(Collectors.toSet());
    }
}