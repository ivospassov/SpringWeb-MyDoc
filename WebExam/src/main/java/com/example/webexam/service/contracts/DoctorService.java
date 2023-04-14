package com.example.webexam.service.contracts;

import com.example.webexam.model.entity.Doctor;
import com.example.webexam.model.entity.Hospital;
import com.example.webexam.model.enums.SpecialtyType;

import java.util.List;
import java.util.Optional;

public interface DoctorService {

    boolean isDoctorListEmpty();

    void seedDoctors();

    List<Doctor> findAllDoctors();

    Optional<Doctor> findDoctorById(Long id);

    List<Doctor> findByCityNameAndSpecialtyName(String city, SpecialtyType specialtyType);

    List<Doctor> findDoctorsByHospital(Hospital hospital);
}
