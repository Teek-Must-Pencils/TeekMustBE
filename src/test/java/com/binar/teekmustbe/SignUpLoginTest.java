package com.binar.teekmustbe;

import com.binar.teekmustbe.controller.SignUpLoginController;
import com.binar.teekmustbe.dto.JwtTokenDto;
import com.binar.teekmustbe.dto.UserLoginDto;
import com.binar.teekmustbe.dto.UserSignupDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class SignUpLoginTest {
    private static final Logger logger = LoggerFactory.getLogger(SignUpLoginTest.class);
    @Autowired
    private SignUpLoginController signUpLoginController;

    @Test
    public void signup() throws IOException {
        signUpLoginController.signUp(new UserSignupDto()
                        .setUsername("testuser")
                        .setPassword("password")
                        .setAddress("Test Ave.")
                        .setEmail("test@test.com")
                        .setRoles(Set.of("buyer")),
                new MockMultipartFile("test_profile_photo",
                        "test_profile_photo.jpg",
                        "application/octet-stream",
                        new ClassPathResource("img/test_profile_photo.jpg")
                                .getInputStream()));
        var response = signUpLoginController.login(new UserLoginDto()
                .setUsername("testuser")
                .setPassword("password"));
        assertEquals("testuser", ((JwtTokenDto)response.getBody()).getUsername());
        assertEquals("test@test.com", ((JwtTokenDto)response.getBody()).getEmail());
        assertEquals("[buyer]", ((JwtTokenDto)response.getBody()).getRole());
        assertNotEquals(null, ((JwtTokenDto)response.getBody()).getAccessToken());

    }
}
