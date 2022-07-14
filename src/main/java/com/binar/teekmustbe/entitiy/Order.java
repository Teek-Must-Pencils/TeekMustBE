package com.binar.teekmustbe.entitiy;

import com.binar.teekmustbe.dto.OrderDto;
import com.binar.teekmustbe.dto.UserSignupDto;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "`order`")
public class Order {
    private static final Logger logger = LoggerFactory.getLogger(Order.class);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private BigDecimal price;
    @ManyToMany
    private List<Product> product = new ArrayList<>();
    private BigDecimal lastOffer;
    private BigDecimal acceptedOffer;
    private Date date;
//    private String buyer;
//    private String seller;

    public Order() {

    }

    public Order(OrderDto orderDto) {
        price = orderDto.getPrice();
        this.product = product;
        lastOffer = orderDto.getLastOffer();
        acceptedOffer = orderDto.getAcceptedOffer();
        date = orderDto.getDate();
//        this.seller = seller;
//        this.buyer = buyer;


    }
}
