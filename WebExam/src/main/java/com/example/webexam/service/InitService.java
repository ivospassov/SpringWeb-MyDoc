package com.example.webexam.service;

import com.example.webexam.model.entity.UserEntity;
import com.example.webexam.model.entity.UserRoleEntity;
import com.example.webexam.model.enums.UserRoleEnum;
import com.example.webexam.repository.UserRepository;
import com.example.webexam.repository.UserRoleRepository;
import com.example.webexam.service.contracts.CityService;
import com.example.webexam.service.contracts.DoctorService;
import com.example.webexam.service.contracts.HospitalService;
import com.example.webexam.service.contracts.SpecialtyService;
import jakarta.annotation.PostConstruct;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class InitService {

  private final UserRoleRepository userRoleRepository;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final CityService cityService;
  private final DoctorService doctorService;
  private final HospitalService hospitalService;
  private final SpecialtyService specialtyService;

  public InitService(UserRoleRepository userRoleRepository,
                     UserRepository userRepository,
                     PasswordEncoder passwordEncoder,
                     @Value("${app.default.password}") String defaultPassword, CityService cityService, DoctorService doctorService, HospitalService hospitalService, SpecialtyService specialtyService) {
    this.userRoleRepository = userRoleRepository;
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.cityService = cityService;
    this.doctorService = doctorService;
    this.hospitalService = hospitalService;
    this.specialtyService = specialtyService;
  }

  @PostConstruct
  public void init() {
    initRoles();
    initUsers();
    this.cityService.seedCities();
    this.hospitalService.seedHospitals();
    this.specialtyService.seedSpecialties();
    this.doctorService.seedDoctors();
  }

  private void initRoles() {
    if (userRoleRepository.count() == 0) {
      var moderatorRole = new UserRoleEntity().setRole(UserRoleEnum.MODERATOR);
      var adminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);

      userRoleRepository.save(moderatorRole);
      userRoleRepository.save(adminRole);
    }
  }

  private void initUsers() {
    if (userRepository.count() == 0) {
      initAdmin();
      initModerator();
      initNormalUser();
    }
  }

  private void initAdmin(){
    var adminUser = new UserEntity().
        setEmail("admin@example.com").
        setFirstName("Admin").
        setLastName("Adminov").
            setCity("Sofia").
        setPassword(passwordEncoder.encode("topsecret")).
        setRoles(userRoleRepository.findAll());

    userRepository.save(adminUser);
  }

  private void initModerator(){

    var moderatorRole = userRoleRepository.
        findUserRoleEntityByRole(UserRoleEnum.MODERATOR).orElseThrow();

    var moderatorUser = new UserEntity().
        setEmail("moderator@example.com").
        setFirstName("Moderator").
        setLastName("Plovdiv").
            setCity("Greece").
        setPassword(passwordEncoder.encode("topsecret")).
        setRoles(List.of(moderatorRole));

    userRepository.save(moderatorUser);
  }

  private void initNormalUser(){

    var normalUser = new UserEntity().
        setEmail("user@example.com").
        setFirstName("User").
        setLastName("Userov").
            setCity("Bourgas").
        setPassword(passwordEncoder.encode("topsecret"));

    userRepository.save(normalUser);
  }
}
