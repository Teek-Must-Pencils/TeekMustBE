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
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Wishlist() {

    }

    public Wishlist(User user, Product product) {
        this.user = user;
        this.product = product;
    }


}
