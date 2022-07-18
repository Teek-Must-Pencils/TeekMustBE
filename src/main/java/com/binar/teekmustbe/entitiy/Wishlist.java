package com.binar.teekmustbe.entitiy;

import com.binar.teekmustbe.dto.WishlistDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Accessors(chain = true)
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;

    public Wishlist() {

    }

    public Wishlist(WishlistDto wishlistDto, User user, Product product) {
        id = wishlistDto.getId();
        this.user = user;
        this.product = product;
    }


}
