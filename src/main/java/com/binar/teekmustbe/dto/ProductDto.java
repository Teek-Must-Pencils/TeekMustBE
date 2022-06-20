package com.binar.teekmustbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Setter
@Getter
@ToString
@Accessors(chain = true)
public class ProductDto {
    @NotNull
    private String productName;
    @NotNull
    private String category;
    @NotNull
    private BigDecimal price;
    @NotNull
    @JsonIgnore
    private MultipartFile img;
    @NotNull
    private String description;
    @NotNull
    private String seller;
    @NotNull
    private String city;


}
