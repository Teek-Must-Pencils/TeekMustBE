package com.binar.teekmustbe.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
public class NotificationDto {
    private String seller;

    private String buyer;

    private Integer firstPrice;

    private Integer lastPrice;

    private String status;

    private String productName;
}
