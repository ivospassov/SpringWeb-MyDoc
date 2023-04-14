package com.example.webexam.model.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRegistrationDTO {

  @NotBlank(message = "First and last name are mandatory!")
  private String firstName;

  @NotBlank(message = "First and last name are mandatory!")
  private String lastName;

  @NotBlank(message = "Email is required!")
  private String email;

  @Size(min = 5, max = 20, message = "Password must be between 5 and 20 characters!")
  private String password;

  public String getFirstName() {
    return firstName;
  }

  public UserRegistrationDTO setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public UserRegistrationDTO setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public UserRegistrationDTO setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public UserRegistrationDTO setPassword(String password) {
    this.password = password;
    return this;
  }
}
