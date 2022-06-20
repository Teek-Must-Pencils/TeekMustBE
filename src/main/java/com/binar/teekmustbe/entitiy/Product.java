package com.binar.teekmustbe.entitiy;

import com.binar.teekmustbe.dto.ProductDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String productName;
    private BigDecimal price;
    @Lob
    private byte[] img;
    private String description;
    private String seller;
    private String city;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Category> category = new HashSet<>();

    public Product(ProductDto productDto) {

    }
    public Product(){

    }
}
