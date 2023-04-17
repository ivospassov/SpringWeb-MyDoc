package com.example.webexam.web.controllers.AuthControllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllersTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void testLoginPageIsRendered() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/users/login"))
                .andExpect(view().name("auth-login"))
                .andExpect(status().isOk());
    }

    @Test
    void testRegistrationPageIsRendered() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/users/register"))
                .andExpect(view().name("auth-register"))
                .andExpect(status().isOk());
    }
}
