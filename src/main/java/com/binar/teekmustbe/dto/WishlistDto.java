package com.binar.teekmustbe.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;


@Setter
@Getter
@ToString
@Accessors(chain = true)
public class WishlistDto {

    private long id;
    private long idUser;
    private long idProduct;
}
