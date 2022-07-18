package com.binar.teekmustbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@ToString
@Accessors(chain = true)
public class ProfileDto {
    @NotNull
    private long id;
    @JsonIgnore
    private MultipartFile img;
    @NotBlank(message = "Alamat harus diisi!")
    private String address;
    @NotBlank(message = "telpon harus diisi!")
    private String number;

    public ProfileDto() {
    }

    public ProfileDto(UserDto userDto) {
        id = userDto.getId();
        address = userDto.getAddress();
        number = userDto.getNumber();
    }


}
