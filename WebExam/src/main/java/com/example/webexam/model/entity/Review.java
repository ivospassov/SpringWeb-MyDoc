package com.example.webexam.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class Review extends BaseEntity {

    @Column(columnDefinition = "TEXT")
    private String comment;

    @ManyToOne
    private UserEntity patient;

    @ManyToOne
    private Doctor doctor;

    public Review() {}

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public UserEntity getUser() {
        return patient;
    }

    public void setUser(UserEntity user) {
        this.patient = user;
    }

    public UserEntity getPatient() {
        return patient;
    }

    public void setPatient(UserEntity patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
