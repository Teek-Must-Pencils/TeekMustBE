package com.binar.teekmustbe.dto;

import com.binar.teekmustbe.entitiy.Offer;
import com.binar.teekmustbe.entitiy.Product;
import com.binar.teekmustbe.entitiy.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Setter
@Getter
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
public class OfferDto {

    private long id;

    private User userId;

    private Product productId;

    private String priceNegotiated;

    private LocalDateTime createdAt;

    public OfferDto(Offer offer) {
        userId = offer.getUserId();
        productId=offer.getProductId();
        priceNegotiated = getPriceNegotiated();
    }

    public OfferDto() {

    }
}
