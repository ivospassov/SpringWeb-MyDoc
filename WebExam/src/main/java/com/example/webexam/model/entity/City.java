package com.example.webexam.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "cities")
public class City extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "city", targetEntity = Doctor.class)
    private Set<Doctor> doctors;

    @OneToMany(mappedBy = "city", targetEntity = Hospital.class)
    private Set<Hospital> hospitals;

    public City() {}

    public City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<Doctor> doctors) {
        this.doctors = doctors;
    }

    public Set<Hospital> getHospitals() {
        return hospitals;
    }

    public void setHospitals(Set<Hospital> hospitals) {
        this.hospitals = hospitals;
    }
}
