package com.binar.teekmustbe.dto;

import com.binar.teekmustbe.entitiy.Category;
import com.binar.teekmustbe.entitiy.Product;
import com.binar.teekmustbe.enums.Roles;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
public class ProductDto {
    private long id;
    @NotNull
    private String name;
    @NotNull
    private Set<String> categories = new HashSet<>();
    @NotNull
    private BigDecimal price;
    @JsonIgnore
    private MultipartFile img;
    @NotNull
    private String description;
    @NotNull
    private String seller;
    private String buyer;
    @NotNull
    private String city;
    private byte[] imgB;

    public ProductDto() {

    }

    public ProductDto(Product product) {
        id = product.getId();
        name = product.getName();
        categories = product.getCategory().stream().map(Category::getCategory).map(Enum::name).collect(Collectors.toSet());
        price = product.getPrice();
        description = product.getDescription();
        product.getUsers().forEach(u -> u.getRoles().forEach(v -> {
            if (v.getRole().name().equals(Roles.SELLER.name())) {
                seller = u.getUsername();
            } else if (v.getRole().name().equals(Roles.BUYER.name())) {
                buyer = u.getUsername();
            }
        }));
        city = product.getCity();
        imgB = product.getImg();
    }

}
