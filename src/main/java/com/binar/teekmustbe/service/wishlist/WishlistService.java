package com.binar.teekmustbe.service.wishlist;

import com.binar.teekmustbe.dto.WishlistDto;
import com.binar.teekmustbe.entitiy.Wishlist;

import java.util.List;
import java.util.Optional;

public interface WishlistService {
    void save(WishlistDto wishlistDto);

    List<Wishlist> findAll();

    Optional<Wishlist> findById(long id);
}
