package com.binar.teekmustbe.dto;

import com.binar.teekmustbe.entitiy.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;


@Setter
@Getter
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
public class OfferDto {

    private long id;

    private long userId;

    private long productId;

    private BigDecimal priceNegotiated;

    private LocalDateTime createdAt;

    private Set<String> status;


    public OfferDto() {

    }

    public OfferDto(Offer offer) {
        id = offer.getId();
        userId = offer.getUser().getId();
        productId = offer.getProduct().getId();
        priceNegotiated=offer.getPriceNegotiated();
        status = offer.getStatus().stream().map(Status::getStatus).map(Enum::name).collect(Collectors.toSet());
    }

//    public OfferDto(Offer offer) {
//        status = offer.getStatus().stream().map(Status::getStatus).map(Enum::name).collect(Collectors.toSet());
//    }


}
