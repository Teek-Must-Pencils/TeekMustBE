package com.binar.teekmustbe.entitiy;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;

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
    private BigDecimal lastOffer;
    private BigDecimal acceptedOffer;
    private Date date;
    @OneToOne
    private User buyer;
    @OneToOne
    private User seller;
}
