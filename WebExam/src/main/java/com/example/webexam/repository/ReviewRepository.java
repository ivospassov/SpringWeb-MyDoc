package com.example.webexam.repository;

import com.example.webexam.model.entity.Doctor;
import com.example.webexam.model.entity.Review;
import com.example.webexam.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<Review> findByDoctorAndPatient(Doctor doctor, UserEntity patient);
}
