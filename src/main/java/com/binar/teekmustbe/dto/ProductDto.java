package com.binar.teekmustbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@ToString
@Accessors(chain = true)
public class ProductDto {
    private long id;
    @NotNull
    private String productName;
    @NotNull
    private Set<String> categories = new HashSet<>();
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
