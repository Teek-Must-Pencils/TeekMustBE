package com.binar.teekmustbe.service.wishlist;

import com.binar.teekmustbe.dto.WishlistDto;
import com.binar.teekmustbe.entitiy.Wishlist;
import com.binar.teekmustbe.repository.WishlistRepository;
import com.binar.teekmustbe.service.product.ProductService;
import com.binar.teekmustbe.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishlistServiceImpl implements WishlistService {
    private static final Logger logger = LoggerFactory.getLogger(WishlistServiceImpl.class);
    @Autowired
    private WishlistRepository wishlistRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    public void save(WishlistDto wishlistDto) {

        var user = userService.findById(wishlistDto.getIdUser());
        var product = productService.findById(wishlistDto.getIdProduct());
        var wishlist = new Wishlist() ;
        wishlistRepository.save(wishlist);
    }
}
