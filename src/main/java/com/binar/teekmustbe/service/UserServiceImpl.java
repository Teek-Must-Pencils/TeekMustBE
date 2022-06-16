package com.binar.teekmustbe.service;

import com.binar.teekmustbe.dto.UserSignupDto;
import com.binar.teekmustbe.entitiy.User;
import com.binar.teekmustbe.enums.Roles;
import com.binar.teekmustbe.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.EnumUtils.getEnumIgnoreCase;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;

    public void save(UserSignupDto userSignupDto) {
        var user = new User(userSignupDto);
        if (userSignupDto.getRoles().isEmpty()) {
            user.getRoles().add(
                    roleService.findByRole(Roles.BUYER).orElseThrow(() ->
                            new RuntimeException("Error: No role Buyer Found"))
            );
        } else {
            logger.info(roleService.findAll().toString());
            userSignupDto.getRoles().forEach(role -> user.getRoles().add(
                    roleService.findByRole(getEnumIgnoreCase(Roles.class, role)).orElseThrow(() ->
                            new RuntimeException("Error: No role " + role + " Found. Use Customer as default."))
            ));
        }
        user.setPassword(userSignupDto.getPassword());
        userRepository.save(user);
    }

    public boolean update(UserSignupDto userSignupDto) {
        return false;
    }

    public List<User> findAll() {
        return null;
    }

    public Optional<User> findById(long id) {
        return Optional.empty();
    }

    public boolean delete(long id) {
        return false;
    }

    public boolean deleteByUsername(String username) {
        return false;
    }

    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }

    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    public boolean existsUsername(String username) {
        return false;
    }

    public boolean existsEmail(String email) {
        return false;
    }
}
