package com.binar.teekmustbe.service.offer;


import com.binar.teekmustbe.dto.OfferDto;
import com.binar.teekmustbe.entitiy.Offer;
import com.binar.teekmustbe.entitiy.User;

import java.util.List;
import java.util.Optional;


public interface OfferService {
    void save(OfferDto offerDto);

    List<Offer> findAll();

    boolean update(Offer offer);

    boolean update(OfferDto offerDto);

    Optional<Offer> findById(long id);
}
