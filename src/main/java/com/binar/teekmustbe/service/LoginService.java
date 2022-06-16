package com.binar.teekmustbe.service;

import com.binar.teekmustbe.dto.UserSignupDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface LoginService {
    void saveUser(UserSignupDto userSignupDto);
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}
