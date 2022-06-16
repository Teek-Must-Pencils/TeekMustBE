package com.binar.teekmustbe.service;

import com.binar.teekmustbe.dto.UserSignupDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements LoginService, UserDetailsService {
    public void saveUser(UserSignupDto userSignupDto) {

    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
