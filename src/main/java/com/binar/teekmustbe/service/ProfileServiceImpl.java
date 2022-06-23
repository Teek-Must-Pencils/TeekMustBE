package com.binar.teekmustbe.service;

import com.binar.teekmustbe.dto.ProfileDto;
import com.binar.teekmustbe.entitiy.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl {
    @Autowired
    private UserService userService;

    public void update(ProfileDto profileDto) {
        var user = new User(profileDto);
        userService.update(user);
    }
}

