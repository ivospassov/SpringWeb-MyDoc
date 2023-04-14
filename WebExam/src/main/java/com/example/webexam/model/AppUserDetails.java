package com.example.webexam.model;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class AppUserDetails extends User {

  private String city;
  private String fullName;

  public AppUserDetails(String username, String password,
      Collection<? extends GrantedAuthority> authorities) {
    super(username, password, authorities);
  }

  public String getCity() {
    return city;
  }

  public AppUserDetails setCity(String city) {
    this.city = city;
    return this;
  }

  public String getFullName() {
    return fullName;
  }

  public AppUserDetails setFullName(String fullName) {
    this.fullName = fullName;
    return this;
  }
}
