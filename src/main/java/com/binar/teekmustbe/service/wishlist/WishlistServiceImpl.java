package com.binar.teekmustbe.service.wishlist;

import com.binar.teekmustbe.dto.WishlistDto;
import com.binar.teekmustbe.entitiy.Offer;
import com.binar.teekmustbe.entitiy.Product;
import com.binar.teekmustbe.entitiy.Wishlist;
import com.binar.teekmustbe.repository.WishlistRepository;
import com.binar.teekmustbe.service.product.ProductService;
import com.binar.teekmustbe.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

        var user = userService.findById(wishlistDto.getUserId()).get();
        var product = productService.findById(wishlistDto.getProductId()).get();
        var wishlist = new Wishlist(wishlistDto, user, product);
        wishlistRepository.save(wishlist);
    }

    public List<Wishlist> findAll() {
        return wishlistRepository.findAll();
    }

    public Optional<Wishlist> findById(long id) {
        if (wishlistRepository.findById(id).isPresent()) {
            return wishlistRepository.findById(id);
        }
        return Optional.empty();
    }
}
