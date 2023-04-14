package com.example.webexam.service.contracts;

import com.example.webexam.model.dtos.ReviewDTO;

public interface ReviewService {

    void leaveReview(Long doctorId, ReviewDTO reviewDTO);
}
