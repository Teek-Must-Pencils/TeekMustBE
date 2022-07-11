package com.binar.teekmustbe.repository;


import com.binar.teekmustbe.entitiy.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface OfferRepo extends JpaRepository<Offer, Long> {
}
