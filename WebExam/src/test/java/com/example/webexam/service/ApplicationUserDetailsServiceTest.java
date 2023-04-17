package com.example.webexam.service;

import com.example.webexam.model.entity.UserEntity;
import com.example.webexam.model.entity.UserRoleEntity;
import com.example.webexam.model.enums.UserRoleEnum;
import com.example.webexam.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opentest4j.AssertionFailedError;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ApplicationUserDetailsServiceTest {
    private static final String EXISTING_EMAIL = "admin@example.com";
    private static final String NON_EXISTING_EMAIL = "non-existing@example.com";

    private com.example.webexam.service.ApplicationUserDetailsService userDetailsServiceTest;

    @Mock
    private UserRepository dummyUserRepository;

    @BeforeEach
    void setup() {
        userDetailsServiceTest = new com.example.webexam.service.ApplicationUserDetailsService(dummyUserRepository);
    }

    @Test
    void testUserIsFound() {

        UserRoleEntity dummyAdminRoleEntity = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);

        UserEntity testEntity = new UserEntity()
                .setEmail(EXISTING_EMAIL)
                .setPassword("secret")
                .setRoles(List.of(dummyAdminRoleEntity));

        when(dummyUserRepository.findUserEntityByEmail(EXISTING_EMAIL))
                .thenReturn(Optional.of(testEntity));

        UserDetails adminDummyUserDetails = userDetailsServiceTest.loadUserByUsername("admin@example.com");
        Assertions.assertNotNull(adminDummyUserDetails);
        Assertions.assertEquals(EXISTING_EMAIL, adminDummyUserDetails.getUsername());
        Assertions.assertEquals("secret", adminDummyUserDetails.getPassword());
        Assertions.assertEquals(1, adminDummyUserDetails.getAuthorities().size());
        assertRole(adminDummyUserDetails.getAuthorities(), "ROLE_ADMIN");
    }

    private void assertRole(Collection<? extends GrantedAuthority> authorities, String role) {
        authorities
                .stream()
                .filter(authority -> role.equals(authority.getAuthority()))
                .findAny()
                .orElseThrow(() -> new AssertionFailedError("Role " + role + " not found!"));
    }

    @Test
    void testUserIsNotFound() {
        assertThrows(UsernameNotFoundException.class,
                () -> {
                    userDetailsServiceTest.loadUserByUsername(NON_EXISTING_EMAIL);
                });
    }
}