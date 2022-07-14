package com.binar.teekmustbe.service.offer;


import com.binar.teekmustbe.entitiy.User;

import java.math.BigDecimal;


public interface OfferService {
    void save(long productId, BigDecimal priceNegotiated, User user);

}
