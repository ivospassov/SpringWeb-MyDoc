package com.example.webexam.web.controllers;

import com.example.webexam.model.dtos.ReviewDTO;
import com.example.webexam.model.entity.Doctor;
import com.example.webexam.service.contracts.DoctorService;
import com.example.webexam.service.contracts.ReviewService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ReviewController {

    private final DoctorService doctorService;
    private final ReviewService reviewService;

    public ReviewController(DoctorService doctorService, ReviewService reviewService) {
        this.doctorService = doctorService;
        this.reviewService = reviewService;
    }

    @ModelAttribute("reviewDTO")
    public ReviewDTO reviewDTO() {
        return new ReviewDTO();
    }

    @GetMapping("/review/{id}")
    public String renderReviewPage(@PathVariable("id") Long id, ModelMap modelMap) {

        Doctor doctor = this.doctorService.findDoctorById(id).get();
        modelMap.addAttribute("doctor", doctor);

        return "reviews";
    }

    @PostMapping("/review/comment/{id}")
    public String handleSubmitReviewRequest(@PathVariable("id") Long id,
                                            @Valid ReviewDTO reviewDTO,
                                            BindingResult bindingResult,
                                            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("reviewDTO", reviewDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.reviewDTO", bindingResult);

            return "redirect:/review";
        }

        this.reviewService.leaveReview(id, reviewDTO);
        return "redirect:/find-my-doc";
    }
}
