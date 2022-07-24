package com.binar.teekmustbe.service.user;

import com.binar.teekmustbe.dto.ProfileDto;
import com.binar.teekmustbe.dto.UserDto;
import com.binar.teekmustbe.dto.UserSignupDto;
import com.binar.teekmustbe.entitiy.User;
import com.binar.teekmustbe.enums.Roles;
import com.binar.teekmustbe.repository.UserRepository;
import com.binar.teekmustbe.service.role.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.EnumUtils.getEnumIgnoreCase;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;

    public void save(UserSignupDto userSignupDto) {
        //
        var user = new User(userSignupDto);
        if (userSignupDto.getRoles().isEmpty()) {
            user.getRoles().add(
                    roleService.findByRole(Roles.BUYER).orElseThrow(() ->
                            new RuntimeException("Error: No role Buyer Found"))
            );
        } else {
            userSignupDto.getRoles().forEach(role -> user.getRoles().add(
                    roleService.findByRole(getEnumIgnoreCase(Roles.class, role)).orElseThrow(() ->
                            new RuntimeException("Error: No role '" + role + "' Found. Use `Buyer` as default."))
            ));
        }
        user.setPassword(userSignupDto.getPassword());
        userRepository.save(user);
    }

    public boolean update(UserDto userDto) {
        if (userRepository.findById(userDto.getId()).isPresent()) {
            userRepository.save(new User(userDto).setId(userDto.getId()).setPassword(userDto.getPassword()));
            return true;
        }
        return false;
    }

    public boolean update(User user) {
        if (userRepository.findById(user.getId()).isPresent()) {
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public boolean update(ProfileDto profileDto) {
        if (findById(profileDto.getId()).isPresent()) {
            var user = findById(profileDto.getId()).get();
            if (profileDto.getAddress() != null)
                user.setAddress(profileDto.getAddress());
            if (profileDto.getNumber() != null)
                user.setNumber(profileDto.getNumber());
            if (profileDto.getImg() != null)
                user.setImg(profileDto.getImg());
            return update(user);
        }
        return false;
    }

    public List<User> findAll() {
        return userRepository.findAllByOrderByIdAsc();
    }

    public Optional<User> findById(long id) {
        if (userRepository.findById(id).isPresent()) {
            return userRepository.findById(id);
        }
        return Optional.empty();
    }

    public boolean delete(long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean deleteByUsername(String username) {
        var user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            userRepository.deleteById(user.get().getId());
            return true;
        }
        return false;
    }

    public Optional<User> findByUsername(String username) {
        if (userRepository.findByUsername(username).isPresent()) {
            return userRepository.findByUsername(username);
        }
        return Optional.empty();
    }

    public Optional<User> findByEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            return userRepository.findByEmail(email);
        }
        return Optional.empty();
    }

    public boolean existsUsername(String username) {
        return findByUsername(username).isPresent();
    }

    public boolean existsEmail(String email) {
        return findByEmail(email).isPresent();
    }

    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}
