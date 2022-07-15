package com.binar.teekmustbe.service.offer;

import com.binar.teekmustbe.dto.OfferDto;
import com.binar.teekmustbe.entitiy.Offer;
import com.binar.teekmustbe.entitiy.Product;
import com.binar.teekmustbe.entitiy.User;
import com.binar.teekmustbe.enums.StatusEnum;
import com.binar.teekmustbe.repository.OfferRepo;
import com.binar.teekmustbe.service.product.ProductService;
import com.binar.teekmustbe.service.status.StatusService;
import com.binar.teekmustbe.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

@Service
public class OfferServiceImpl implements OfferService{

    @Autowired
    private OfferRepo offerRepo;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private StatusService statusService;


//    public void save(long productId, BigDecimal priceNegotiated, User user) {
//        var product = productService.findProductById(productId);
//        var offer = new Offer();
//        offer.setUser(user);
//        offer.setProduct(product);
//        offer.setPriceNegotiated(priceNegotiated);
//        offer.setStatus(StatusEnum.WAITING);
//        offerRepo.save(offer);
//    }

    @Override
    public void save(OfferDto offerDto) {
        var user = userService.findById(offerDto.getUserId());
        var product=productService.findById(offerDto.getProductId());
//        var status = statusService.findByStatus(offerDto.setStatus(StatusEnum.WAITING));
        var offer= new Offer();
        offerRepo.save(offer);
    }
}