package com.example.webexam.service.contracts;

import com.example.webexam.model.entity.Hospital;

import java.util.List;
import java.util.Optional;

public interface HospitalService {

    boolean isHospitalListEmpty();

    void seedHospitals();

    Hospital findById(Long id);

    Optional<Hospital> findByName(String name);

    List<Hospital> findAllHospitals();

    List<Hospital> findByCity(String cityName);

    void updateHospitalStatistics(Long hospitalId);

}
