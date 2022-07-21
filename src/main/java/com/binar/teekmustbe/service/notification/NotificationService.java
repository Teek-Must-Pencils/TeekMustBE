package com.binar.teekmustbe.service.notification;

import com.binar.teekmustbe.dto.NotificationDto;

import java.util.List;

public interface NotificationService {
    List<NotificationDto> notificationBuyer(String username);
    List<NotificationDto> notificationSeller(String username);

}
