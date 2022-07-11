package com.binar.teekmustbe.entitiy;

import com.binar.teekmustbe.dto.OfferDto;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User userId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product productId;

    private String priceNegotiated;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Offer(OfferDto offerDto) {
//        userId = offerDto.getUserId();
//        productId=offerDto.getProductId();
//        priceNegotiated = getPriceNegotiated();

    }

    public Offer() {

    }


}
