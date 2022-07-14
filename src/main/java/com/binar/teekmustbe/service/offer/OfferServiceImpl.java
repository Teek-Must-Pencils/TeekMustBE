package com.binar.teekmustbe.service.offer;

import com.binar.teekmustbe.dto.OfferDto;
import com.binar.teekmustbe.entitiy.Offer;
import com.binar.teekmustbe.entitiy.Product;
import com.binar.teekmustbe.entitiy.User;
import com.binar.teekmustbe.enums.StatusEnum;
import com.binar.teekmustbe.repository.OfferRepo;
import com.binar.teekmustbe.service.product.ProductService;
import com.binar.teekmustbe.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OfferServiceImpl implements OfferService{

    @Autowired
    private OfferRepo offerRepo;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    public void save(long productId, BigDecimal priceNegotiated, User user) {
        var product = productService.findProductById(productId);
        var offer = new Offer();
        offer.setUserId(user);
        offer.setProductId(product);
        offer.setPriceNegotiated(priceNegotiated);
        offer.setStatus(StatusEnum.WAITING);
        offerRepo.save(offer);
    }


}
