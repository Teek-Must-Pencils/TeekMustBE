package com.binar.teekmustbe.service;

import com.binar.teekmustbe.dto.LoginDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface LoginService {
    void saveUser(LoginDto loginDto);
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}
