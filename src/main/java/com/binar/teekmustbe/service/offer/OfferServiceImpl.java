package com.binar.teekmustbe.service.offer;

import com.binar.teekmustbe.dto.OfferDto;
import com.binar.teekmustbe.dto.ProfileDto;
import com.binar.teekmustbe.entitiy.Offer;
import com.binar.teekmustbe.entitiy.Status;
import com.binar.teekmustbe.entitiy.User;
import com.binar.teekmustbe.enums.Roles;
import com.binar.teekmustbe.enums.StatusEnum;
import com.binar.teekmustbe.repository.OfferRepository;
import com.binar.teekmustbe.service.product.ProductService;
import com.binar.teekmustbe.service.status.StatusService;
import com.binar.teekmustbe.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.EnumUtils.getEnumIgnoreCase;

@Service
public class OfferServiceImpl implements OfferService {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private StatusService statusService;


    public void save(OfferDto offerDto) {
        var user = userService.findById(offerDto.getUserId()).get();
        var product = productService.findById(offerDto.getProductId()).get();
        var status = statusService.findByStatus(StatusEnum.WAITING).get();
        var offer = new Offer(offerDto, user, product, status);
        offerRepository.save(offer);
    }

    public List<Offer> findAll() {
        return offerRepository.findAll();
    }

    public boolean update(Offer offer) {
        if (offerRepository.findById(offer.getId()).isPresent()) {
            offerRepository.save(offer);
            return true;
        }
        return false;
    }

    public Optional<Offer> findById(long id) {
        if (offerRepository.findById(id).isPresent()) {
            return offerRepository.findById(id);
        }
        return Optional.empty();
    }


    public boolean update(OfferDto offerDto) {

        var offer = findById(offerDto.getId()).get();

        if (offerDto.getStatus().isEmpty()) {
            offer.setStatus(statusService.findByStatus(StatusEnum.WAITING).orElseThrow(() ->
                    new RuntimeException("Error: No status 'waiting' Found"))
            );
        } else {
            var status = getEnumIgnoreCase(StatusEnum.class, offerDto.getStatus().toUpperCase());
            offer.setStatus(statusService.findByStatus(status).orElseThrow(() ->
                    new RuntimeException("Error: No status " + status + " Found"))
            );
        }
        return update(offer);
    }
}