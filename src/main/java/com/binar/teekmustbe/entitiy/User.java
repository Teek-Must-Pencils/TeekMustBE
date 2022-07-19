package com.binar.teekmustbe.entitiy;

import com.binar.teekmustbe.dto.ProfileDto;
import com.binar.teekmustbe.dto.UserDto;
import com.binar.teekmustbe.dto.UserSignupDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "`user`")
@Accessors(chain = true)
@ToString
public class User {
    private static final Logger logger = LoggerFactory.getLogger(User.class);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    private String email;
    private String address;
    private String number;
    @Lob
    private byte[] img;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();
    public User() {

    }

    public User(UserSignupDto userSignupDto) {
        email = userSignupDto.getEmail();
        username = userSignupDto.getUsername();
        address = userSignupDto.getAddress();
        number = userSignupDto.getNumber();
        try {
            img = userSignupDto.getImg().getBytes();
        } catch (IOException ioException) {
            logger.error(ioException.getMessage());
        }
    }

    public User(UserDto userDto) {
        id = userDto.getId();
        email = userDto.getEmail();
        username = userDto.getUsername();
        address = userDto.getAddress();
        number = userDto.getNumber();
        if (userDto != null) {
            img = userDto.getImgB();
        } else {
            try {
                img = userDto.getImg().getBytes();
            } catch (IOException ioException) {
                logger.error(ioException.getMessage());
            }
        }
    }

    public User(ProfileDto profileDto){
        address = profileDto.getAddress();
        number = profileDto.getNumber();
        try {
            img = profileDto.getImg().getBytes();
        } catch (IOException ioException) {
            logger.error(ioException.getMessage());
        }
    }
}
