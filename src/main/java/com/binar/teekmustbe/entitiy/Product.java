package com.binar.teekmustbe.entitiy;

import com.binar.teekmustbe.dto.ProductDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@ToString
public class Product {
    private static final Logger logger = LoggerFactory.getLogger(User.class);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private BigDecimal price;
    private String description;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<User> users = new HashSet<>();
    private String city;
    @Lob
    private byte[] img;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Category> category = new HashSet<>();

    public Product(ProductDto productDto, User seller) {
        name = productDto.getName();
        price = productDto.getPrice();
        description = productDto.getDescription();
        users.add(seller);
        city = productDto.getCity();
        img = productDto.getImg();
    }

    public Product() {

    }
}
