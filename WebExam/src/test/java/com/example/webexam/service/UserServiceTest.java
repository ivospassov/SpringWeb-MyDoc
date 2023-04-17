package com.example.webexam.service;

import com.example.webexam.model.entity.UserEntity;
import com.example.webexam.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

class UserServiceTest {

    @Mock
    private PasswordEncoder passwordEncoderDummy = new BCryptPasswordEncoder();

    @Mock
    private UserRepository userRepositoryDummy;

    @Mock
    private UserDetailsService userDetailsServiceDummy;

    @Captor
    private ArgumentCaptor<UserEntity> userEntityArgumentCaptor;

    private UserService userServiceDummy;


    @BeforeEach
    void setup() {
        userServiceDummy = new UserService(
                userRepositoryDummy,
                passwordEncoderDummy,
                userDetailsServiceDummy);
    }

//    @Test
//    void testUserRegister() {
//
//        String testPassword = "secret";
//        String actualPassword = "encodedPassword";
//
//        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO();
//        userRegistrationDTO.setEmail("test@example.com");
//        userRegistrationDTO.setPassword(passwordEncoderDummy.encode(testPassword));
//        userRegistrationDTO.setFirstName("First");
//        userRegistrationDTO.setLastName("Last");
//
////        when(passwordEncoderDummy.encode(userRegistrationDTO.getPassword()))
////                .thenReturn(actualPassword);
//
//        userServiceDummy.registerUser(userRegistrationDTO);
//
//        UserEntity actualRegisteredUser = userEntityArgumentCaptor.getValue();
//        Assertions.assertEquals(userRegistrationDTO.getEmail(), actualRegisteredUser.getEmail());
////        Assertions.assertEquals(actualPassword, actualRegisteredUser.getPassword());
//    }
}