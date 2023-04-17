package com.example.webexam.service;

import com.example.webexam.service.contracts.ContactService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContactServiceImplTest {

    @Mock
    private ContactService contactService;

    @Test
    void testSendMessage() {

        doNothing().when(contactService).sendMessage(any(String.class));
        contactService.sendMessage("This is a dummy message");
    }
}