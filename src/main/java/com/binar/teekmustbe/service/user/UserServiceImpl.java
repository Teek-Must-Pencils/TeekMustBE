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

    public boolean update(ProfileDto profileDto) {
        if (findById(profileDto.getId()).isPresent()) {
            var user = findById(profileDto.getId()).get();
            user.setAddress(profileDto.getAddress())
                    .setNumber(profileDto.getNumber())
                    .setImg(profileDto.getImg());
            return update(user);
        }
        return false;
    }

    public List<UserDto> findAll() {
        return userRepository.findAllByOrderByIdAsc().stream().map(UserDto::new).collect(Collectors.toList());
    }

    public Optional<UserDto> findById(long id) {
        if (userRepository.findById(id).isPresent()) {
            return Optional.of(new UserDto(userRepository.findById(id).get()));
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

    public Optional<UserDto> findByUsername(String username) {
        if (userRepository.findByUsername(username).isPresent()) {
//            logger.info(userRepository.findByUsername(username).get().getPassword());
            return Optional.of(new UserDto(userRepository.findByUsername(username).get()));
        }
        return Optional.empty();
    }

    public Optional<UserDto> findByEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            return Optional.of(new UserDto(userRepository.findByEmail(email).get()));
        }
        return Optional.empty();
    }

    public boolean existsUsername(String username) {
        return findByUsername(username).isPresent();
    }

    public boolean existsEmail(String email) {
        return findByEmail(email).isPresent();
    }
}
