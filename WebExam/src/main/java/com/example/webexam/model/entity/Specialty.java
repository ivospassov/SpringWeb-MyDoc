package com.example.webexam.model.entity;

import com.example.webexam.model.enums.SpecialtyType;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "specialties")
public class Specialty extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SpecialtyType name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "specialty", targetEntity = Doctor.class)
    private Set<Doctor> doctors;

    public Specialty() {}

    public Specialty(SpecialtyType name) {
        this.name = name;
    }

    public SpecialtyType getName() {
        return name;
    }

    public void setName(SpecialtyType name) {
        this.name = name;
    }

    public Set<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<Doctor> doctors) {
        this.doctors = doctors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
