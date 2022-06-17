package com.binar.teekmustbe.service;

import com.binar.teekmustbe.dto.UserSignupDto;
import com.binar.teekmustbe.entitiy.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void save(UserSignupDto userSignupDto);
    boolean update(UserSignupDto userSignupDto);
    List<User> findAll();
    Optional<User> findById(long id);
    boolean delete(long id);
    boolean deleteByUsername(String username);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    boolean existsUsername(String username);
    boolean existsEmail(String email);

}