package com.binar.teekmustbe.service.notification;

import com.binar.teekmustbe.dto.NotificationDto;
import com.binar.teekmustbe.repository.OfferRepository;
import com.binar.teekmustbe.service.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    OfferRepository offerRepository;

    @Override
    public List<NotificationDto> notificationBuyer(String username) {
        List<Object[]> data = offerRepository.notification();
        List<NotificationDto> listNotification = new ArrayList<>();
        for(Object[] i: data){
            if(i[4].equals(username)){
                NotificationDto notificationDto = new NotificationDto();
                notificationDto.setProduct((String) i[0]);
                notificationDto.setFirstPrice((BigDecimal) i[1]);
                notificationDto.setLastPrice((BigDecimal) i[2]);
                notificationDto.setStatus((String) i[3]);
                notificationDto.setBuyer((String) i[4]);
                notificationDto.setSeller((String) i[5]);
                listNotification.add(notificationDto);
            }
        }
        return listNotification;
    }

    @Override
    public List<NotificationDto> notificationSeller(String username) {
        List<Object[]> data = offerRepository.notification();
        List<NotificationDto> listNotification = new ArrayList<>();
        for(Object[] i: data){
            if(i[5].equals(username)){
                NotificationDto notificationDto = new NotificationDto();
                notificationDto.setProduct((String) i[0]);
                notificationDto.setFirstPrice((BigDecimal) i[1]);
                notificationDto.setLastPrice((BigDecimal) i[2]);
                notificationDto.setStatus((String) i[3]);
                notificationDto.setBuyer((String) i[4]);
                notificationDto.setSeller((String) i[5]);
                listNotification.add(notificationDto);
            }
        }
        return listNotification;
    }
}
