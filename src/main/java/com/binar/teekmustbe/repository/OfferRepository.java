package com.binar.teekmustbe.repository;


import com.binar.teekmustbe.dto.NotificationDto;
import com.binar.teekmustbe.entitiy.Offer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findAll();


    @Query(value = "select p.\"name\" as productName,o.price_negotiated as lastprice,p.price  as firstprice,s.status as status,(select username from \"user\" where \"user\".id = o.user_id) as buyer,u.username as seller from offer o ,product p, offer_status os ,status s ,product_users pu, \"user\" u where o.id = os.offer_id and os.status_id =s.id and o.product_id = p.id and p.id = pu.product_id and u.id = pu.users_id",nativeQuery = true)
    List<Object[]> notification();

}
