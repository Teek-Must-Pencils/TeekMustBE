package com.binar.teekmustbe;

import com.binar.teekmustbe.controller.ProfileController;
import com.binar.teekmustbe.controller.SignUpLoginController;
import com.binar.teekmustbe.controller.UserController;
import com.binar.teekmustbe.dto.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Objects;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class ProfileTest {
    @Autowired
    private SignUpLoginController signUpLoginController;
    @Autowired
    private ProfileController profileController;
    @Autowired
    private UserController userController;

    @Test
    public void profileTest() throws IOException {
        signUpLoginController.signUp(new UserSignupDto()
                        .setUsername("testuser")
                        .setPassword("password")
                        .setAddress("Test Ave.")
                        .setEmail("test@test.com")
                        .setRoles(Set.of("buyer"))
                        .setNumber("1234567"),
                new MockMultipartFile("test_profile_photo",
                        "test_profile_photo.jpg",
                        "application/octet-stream",
                        new ClassPathResource("img/test_profile_photo.jpg")
                                .getInputStream()));
        profileController.update(new ProfileDto()
                .setAddress("tesAddress")
                .setNumber("7654321"),
                new MockMultipartFile("test_profile_photo",
                        "test_profile_photo.jpg",
                        "application/octet-stream",
                        new ClassPathResource("img/test_profile_photo.jpg")
                                .getInputStream()));
        var response = userController.findByUsername("testuser");
        assertEquals("tesAddress", ((UserDto) Objects.requireNonNull(response.getBody())).getAddress());
        assertEquals("7654321", ((UserDto) Objects.requireNonNull(response.getBody())).getNumber());

    }
}
