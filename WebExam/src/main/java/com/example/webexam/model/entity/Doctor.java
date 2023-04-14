package com.example.webexam.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.util.Set;

@Entity
@Table(name = "doctors")
public class Doctor extends BaseEntity {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private Integer age;

    @ManyToOne
    private Specialty specialty;

    @Column(columnDefinition = "TEXT")
    private String biography;

    @Basic
    private String photoUrl;

    @Column(nullable = false)
    @Email
    private String email;

    @ManyToOne
    private City city;

    @ManyToOne
    private Hospital hospital;

    @OneToMany(mappedBy = "doctor", targetEntity = Appointment.class, fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Set<Appointment> appointments;

    public Doctor() {}

    public Doctor(String firstName, String lastName, Integer age,
                  Specialty specialty, String biography, String email,
                  City city, Hospital hospital, String photoUrl) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.specialty = specialty;
        this.biography = biography;
        this.email = email;
        this.city = city;
        this.hospital = hospital;
        this.photoUrl = photoUrl;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialtyType) {
        this.specialty = specialtyType;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }
}
