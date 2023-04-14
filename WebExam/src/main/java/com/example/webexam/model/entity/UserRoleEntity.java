package com.example.webexam.model.entity;

import com.example.webexam.model.enums.UserRoleEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class UserRoleEntity extends BaseEntity {

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private UserRoleEnum role;


  public UserRoleEnum getRole() {
    return role;
  }

  public UserRoleEntity setRole(UserRoleEnum role) {
    this.role = role;
    return this;
  }

  @Override
  public String toString() {
    return "UserRoleEntity{" +
        "id=" + super.getId() +
        ", role=" + role +
        '}';
  }
}
