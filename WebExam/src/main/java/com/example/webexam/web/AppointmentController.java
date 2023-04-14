package com.example.webexam.web;

import com.example.webexam.model.dtos.AppointmentDTO;
import com.example.webexam.model.entity.Appointment;
import com.example.webexam.model.entity.Doctor;
import com.example.webexam.service.contracts.AppointmentService;
import com.example.webexam.service.contracts.DoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final DoctorService doctorService;

    public AppointmentController(AppointmentService appointmentService, DoctorService doctorService) {
        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
    }

    @ModelAttribute("appointmentDTO")
    public AppointmentDTO appointmentDTO() {
        return new AppointmentDTO();
    }

    @GetMapping("/doctors/appointments/{id}")
    public ModelAndView renderAppointmentSection(@PathVariable(name = "id") Long id, ModelAndView modelAndView) {
        modelAndView.addObject("docId", id);
        modelAndView.setViewName("appointment-form");

        return modelAndView;
    }

    @PostMapping("/appointment/book/{id}")
    public String handleAppointmentForm(@PathVariable(name = "id") Long doctorId,
                                        AppointmentDTO appointmentDTO) {

        //TODO: set up functionality for checking available dates and times accordingly --> no avail. times will DISABLE the corresponding date
        //TODO: use the id from the path to find the doctor and his appointment (reason: to achieve above task)

        //Persist the appointment
        Doctor doctor = this.doctorService.findDoctorById(doctorId).get();
        this.appointmentService.seedAppointment(appointmentDTO, doctor);


        //TODO: show pop-up message for successful booking
        return "redirect:/find-my-doc";
    }

    @GetMapping("/appointments-list")
    public String renderAppointmentList(ModelMap modelMap) {

        List<Appointment> appointmentList = this.appointmentService.getByPatientId();
        modelMap.addAttribute("appointments", appointmentList);

        return "/appointments-list";
    }

    @GetMapping("/appointments-list/cancel/{id}")
    public String handleCancelAppointmentAction(@PathVariable("id") Long id) {
        this.appointmentService.cancel(id);

        return "redirect:/appointments-list";
    }
}
