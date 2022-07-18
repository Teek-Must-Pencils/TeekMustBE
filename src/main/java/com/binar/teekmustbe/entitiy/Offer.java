package com.binar.teekmustbe.entitiy;

import com.binar.teekmustbe.dto.OfferDto;
import com.binar.teekmustbe.enums.StatusEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;

    private BigDecimal priceNegotiated;

    @CreationTimestamp  
    private LocalDateTime createdAt;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Status> status = new HashSet<>();

    public Offer(OfferDto offerDto, User user, Product product, Status status) {
        id = offerDto.getId();
        this.user = user;
        this.product = product;
        this.status.add(status);
        priceNegotiated = offerDto.getPriceNegotiated();

    }
    public Offer(){

    }

//    public Offer(){
//
//    }
}
