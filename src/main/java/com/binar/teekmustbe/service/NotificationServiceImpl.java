package com.binar.teekmustbe.service;

import com.binar.teekmustbe.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService{

    @Autowired
    OfferRepository offerRepository;


}
