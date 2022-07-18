package com.binar.teekmustbe.repository;


import com.binar.teekmustbe.entitiy.Offer;
import com.binar.teekmustbe.entitiy.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findAll();

}
