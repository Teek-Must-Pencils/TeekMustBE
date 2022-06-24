package com.binar.teekmustbe.service;

import com.binar.teekmustbe.dto.UserDto;
import com.binar.teekmustbe.dto.UserSignupDto;
import com.binar.teekmustbe.entitiy.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void save(UserSignupDto userSignupDto);

    boolean update(UserDto userDto);

    List<UserDto> findAll();

    Optional<UserDto> findById(long id);

    boolean delete(long id);

    boolean deleteByUsername(String username);

    Optional<UserDto> findByUsername(String username);

    Optional<UserDto> findByEmail(String email);

    boolean existsUsername(String username);

    boolean existsEmail(String email);

}
