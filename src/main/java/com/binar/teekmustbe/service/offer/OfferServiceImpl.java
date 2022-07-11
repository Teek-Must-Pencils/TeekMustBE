package com.binar.teekmustbe.service.offer;

import com.binar.teekmustbe.dto.OfferDto;
import com.binar.teekmustbe.entitiy.Offer;
import com.binar.teekmustbe.entitiy.Product;
import com.binar.teekmustbe.repository.OfferRepo;
import com.binar.teekmustbe.service.product.ProductService;
import com.binar.teekmustbe.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService{

    @Autowired
    private OfferRepo offerRepo;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    public void save(OfferDto offerDto  ) {

        var offer = new Offer();
        offer.setUserId(offerDto.getUserId());
        offer.setProductId(offerDto.getProductId());
        offer.setCreatedAt(offerDto.getCreatedAt());
        offer.setPriceNegotiated(offerDto.getPriceNegotiated());
        offerRepo.save(offer);
    }
}
