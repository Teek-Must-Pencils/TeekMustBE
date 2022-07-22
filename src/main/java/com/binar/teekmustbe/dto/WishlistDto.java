package com.binar.teekmustbe.dto;


import com.binar.teekmustbe.entitiy.Wishlist;
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

    private long userId;

    private long productId;

    public WishlistDto() {

    }

    public WishlistDto(Wishlist wishlist) {
        id = wishlist.getId();
        userId = wishlist.getUser().getId();
        productId = wishlist.getProduct().getId();
    }

}
