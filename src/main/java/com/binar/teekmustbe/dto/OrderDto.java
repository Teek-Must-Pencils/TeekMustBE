package com.binar.teekmustbe.dto;

import com.binar.teekmustbe.entitiy.Product;
import com.binar.teekmustbe.entitiy.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@ToString

@Accessors(chain = true)
public class OrderDto {
    private long id;
    private BigDecimal price;
    private BigDecimal lastOffer;
    private BigDecimal acceptedOffer;
    private Date date;
    private String buyer;
    private String seller;
    private Product product;
}
