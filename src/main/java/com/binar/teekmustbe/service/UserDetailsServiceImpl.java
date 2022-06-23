package com.binar.teekmustbe.service;

import com.binar.teekmustbe.dto.UserSignupDto;
import com.binar.teekmustbe.entitiy.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    @Autowired
    UserService userService;
    public void saveUser(UserSignupDto userSignupDto) {
        userService.save(userSignupDto);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userService.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User " + username + " not found.");
        } else {
            logger.info("Found user : " + username);
            return new UserDetailsImpl(user.get());
        }
    }
}
