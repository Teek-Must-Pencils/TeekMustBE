package com.binar.teekmustbe.service.offer;


import com.binar.teekmustbe.entitiy.User;


public interface OfferService {
    void save(long productId, Integer priceNegotiated, User user);

}
