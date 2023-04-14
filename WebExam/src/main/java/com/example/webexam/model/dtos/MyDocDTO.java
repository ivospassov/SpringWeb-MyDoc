package com.example.webexam.model.dtos;

import com.example.webexam.model.enums.CitiesName;
import com.example.webexam.model.enums.SpecialtyType;

public class MyDocDTO {

    private CitiesName location;

    private SpecialtyType specialty;

    private String hospital;

    public MyDocDTO() {}

    public CitiesName getLocation() {
        return location;
    }

    public void setLocation(CitiesName location) {
        this.location = location;
    }

    public SpecialtyType getSpecialty() {
        return specialty;
    }

    public void setSpecialty(SpecialtyType specialty) {
        this.specialty = specialty;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }
}
