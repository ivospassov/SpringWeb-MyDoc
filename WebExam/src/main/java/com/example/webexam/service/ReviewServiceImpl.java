package com.example.webexam.service;

import com.example.webexam.model.dtos.ReviewDTO;
import com.example.webexam.model.entity.Doctor;
import com.example.webexam.model.entity.Review;
import com.example.webexam.model.entity.UserEntity;
import com.example.webexam.repository.ReviewRepository;
import com.example.webexam.repository.UserRepository;
import com.example.webexam.service.contracts.DoctorService;
import com.example.webexam.service.contracts.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final DoctorService doctorService;
    private final ModelMapper modelMapper;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    public ReviewServiceImpl(DoctorService doctorService, ModelMapper modelMapper, ReviewRepository reviewRepository, UserRepository userRepository) {
        this.doctorService = doctorService;
        this.modelMapper = modelMapper;
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void leaveReview(Long doctorId, ReviewDTO reviewDTO) {
        Doctor doctor = this.doctorService.findDoctorById(doctorId).get();

        Optional<Review> optionalReview = this.reviewRepository.findByDoctorAndPatient(doctor, retrieveUserByEmail());

        Review review;

        //If appointment exists, overwrite the current comment
        if (optionalReview.isPresent()) {
            review = optionalReview.get();
            review.setComment(reviewDTO.getComment());

        } else {
            review = this.modelMapper.map(reviewDTO, Review.class);
            review.setDoctor(doctor);
            review.setPatient(retrieveUserByEmail());

        }
        this.reviewRepository.save(review);
    }

    private String getEmailFromAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }

        return null;
    }

    private UserEntity retrieveUserByEmail() {
        String email = getEmailFromAuthentication();

        return this.userRepository.findUserEntityByEmail(email).orElse(null);
    }
}
