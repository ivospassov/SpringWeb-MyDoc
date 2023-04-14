package com.example.webexam.service.contracts;

import com.example.webexam.model.entity.Specialty;

import java.util.List;

public interface SpecialtyService {

    boolean isSpecialtyListEmpty();

    void seedSpecialties();

    List<Specialty> findAll();
}
