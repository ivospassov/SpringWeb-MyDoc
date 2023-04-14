package com.example.webexam.service;

import com.example.webexam.model.entity.Message;
import com.example.webexam.repository.MessageRepository;
import com.example.webexam.service.contracts.ContactService;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {

    private final MessageRepository messageRepository;

    public ContactServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public void sendMessage(String message) {
        Message newMessage = new Message();
        newMessage.setMessage(message);

        this.messageRepository.save(newMessage);
    }
}
