package com.example.webexam.repository;

import com.example.webexam.model.entity.Doctor;
import com.example.webexam.model.entity.Hospital;
import com.example.webexam.model.enums.SpecialtyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    List<Doctor> findByCityNameAndSpecialtyName(String city, SpecialtyType specialty);

    List<Doctor> findAllByHospital(Hospital hospital);
}
