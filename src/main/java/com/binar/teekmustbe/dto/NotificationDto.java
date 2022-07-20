package com.binar.teekmustbe.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
public class NotificationDto {
    private String product;

    private BigDecimal lastPrice;

    private BigDecimal firstPrice;

    private String status;

    private String seller;

    private String buyer;
}
