package com.example.webexam.repository;

import java.util.Optional;

import com.example.webexam.model.entity.UserRoleEntity;
import com.example.webexam.model.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
  Optional<UserRoleEntity> findUserRoleEntityByRole(UserRoleEnum role);
}
