package com.example.webexam.web.controllers;

import com.example.webexam.model.entity.Doctor;
import com.example.webexam.model.entity.Hospital;
import com.example.webexam.service.contracts.DoctorService;
import com.example.webexam.service.contracts.HospitalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class HospitalController {

    private final HospitalService hospitalService;
    private final DoctorService doctorService;

    public HospitalController(HospitalService hospitalService, DoctorService doctorService) {
        this.hospitalService = hospitalService;
        this.doctorService = doctorService;
    }

    @GetMapping("/hospitals/{id}")
    public String renderHospitalById(@PathVariable("id") Long id, ModelMap modelMap) {
        Hospital hospital = getHospital(id);

        modelMap.addAttribute("hospitalId", hospital.getId());
        modelMap.addAttribute("hospitalName", hospital.getName());
        modelMap.addAttribute("hospitalImage", hospital.getImage());
        return "hospital";
    }

    //Get Doctors of a specific hospital
    @GetMapping("/hospital/{id}")
    public String renderDoctorsByHospital(@PathVariable("id") Long hospitalId, RedirectAttributes redirectAttributes) {
        Hospital hospital = getHospital(hospitalId);

        List<Doctor> doctorsByHospital = this.doctorService.findDoctorsByHospital(hospital);
        redirectAttributes.addFlashAttribute("doctors", doctorsByHospital);

        return "redirect:/doctors";
    }

    private Hospital getHospital(Long id) {
        return hospitalService.findById(id);
    }
}
