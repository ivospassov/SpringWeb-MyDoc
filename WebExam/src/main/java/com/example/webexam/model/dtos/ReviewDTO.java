package com.example.webexam.model.dtos;

import jakarta.validation.constraints.Size;

public class ReviewDTO {

    private String doctorId;

    @Size(max = 150)
    private String comment;

    public ReviewDTO() {}

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
