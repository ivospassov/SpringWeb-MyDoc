package com.example.webexam.web;

import com.example.webexam.model.dtos.MyDocDTO;
import com.example.webexam.model.entity.Doctor;
import com.example.webexam.model.entity.Hospital;
import com.example.webexam.model.enums.SpecialtyType;
import com.example.webexam.service.contracts.DoctorService;
import com.example.webexam.service.contracts.HospitalService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class FindMyDocController {

    private final DoctorService doctorService;
    private final HospitalService hospitalService;

    public FindMyDocController(DoctorService doctorService, HospitalService hospitalService) {
        this.doctorService = doctorService;
        this.hospitalService = hospitalService;
    }

    @ModelAttribute("myDocDTO")
    public MyDocDTO myDocDTO() {
        return new MyDocDTO();
    }

    @GetMapping("/find-my-doc")
    public String renderFindMyDocPage() {
        return "my-doc";
    }

    @PostMapping("/find-my-doc")
    public String handleFindMyDocForm(MyDocDTO myDocDTO, ModelMap modelMap) {

        Optional<Hospital> optionalHospital = hospitalService.findByName(myDocDTO.getHospital());
        boolean isHospitalFieldNotEmpty = myDocDTO.getHospital().length() != 0;

        //Finding a particular hospital is a priority over the other filters
        if (optionalHospital.isEmpty() && isHospitalFieldNotEmpty) {
            return "redirect:/404";
        } else if (optionalHospital.isPresent() && isHospitalFieldNotEmpty) {
            return "redirect:/hospitals/" + optionalHospital.get().getId();
        } else if (myDocDTO.getLocation() != null && myDocDTO.getSpecialty() != null) {

            SpecialtyType specialty = myDocDTO.getSpecialty();
            String location = myDocDTO.getLocation().name();

            List<Doctor> filteredDoctorsByCityAndSpecialty = this.doctorService.findByCityNameAndSpecialtyName(location, specialty);
            modelMap.addAttribute("doctors", filteredDoctorsByCityAndSpecialty);
            return "/doctors";
        }
        return "redirect:/find-my-doc";
    }

}
