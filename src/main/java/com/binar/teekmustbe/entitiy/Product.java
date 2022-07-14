package com.binar.teekmustbe.entitiy;

import com.binar.teekmustbe.dto.ProductDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
public class Product {
    private static final Logger logger = LoggerFactory.getLogger(User.class);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private BigDecimal price;
    private String description;
////    @OneToOne
//    private User seller;
    private String seller;
    private String city;
    @Lob
//    @JsonIgnore
    private byte[] img;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Category> category = new HashSet<>();


    public Product(ProductDto productDto) {
        name = productDto.getName();
        price = productDto.getPrice();
        description = productDto.getDescription();
//        this.seller = seller;
        seller = productDto.getSeller();
        city = productDto.getCity();
        try {
            img = productDto.getImg().getBytes();
        } catch (IOException ioException) {
            logger.error(ioException.getMessage());
        }
    }

    public Product(){

    }
}
