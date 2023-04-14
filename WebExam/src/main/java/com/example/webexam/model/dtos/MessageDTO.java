package com.example.webexam.model.dtos;

import jakarta.validation.constraints.NotBlank;

public class MessageDTO {

    private String city;

    private String message;

    public MessageDTO() {}

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
