package com.example.webexam.service;

import com.example.webexam.model.dtos.AppointmentDTO;
import com.example.webexam.model.entity.Appointment;
import com.example.webexam.model.entity.Doctor;
import com.example.webexam.model.entity.UserEntity;
import com.example.webexam.repository.AppointmentRepository;
import com.example.webexam.repository.DoctorRepository;
import com.example.webexam.repository.UserRepository;
import com.example.webexam.service.contracts.AppointmentService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository,
                                  UserRepository userRepository, DoctorRepository doctorRepository,
                                  ModelMapper modelMapper) {
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
        this.doctorRepository = doctorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedAppointment(AppointmentDTO appointmentDTO, Doctor doctor) {
        Appointment appointment = this.modelMapper.map(appointmentDTO, Appointment.class);

        doctor.getAppointments().add(appointment);
        appointment.setDoctor(doctor);
        appointment.setPatient(retrieveUserByEmail());
        appointment.setStatus("Going");
        appointment.setPurposeOfVisit(appointmentDTO.getPurposeOfVisit());

        this.doctorRepository.save(doctor);
    }

    @Override
    public List<Appointment> getByPatientId() {
        return this.appointmentRepository.findAllByPatientId(retrieveUserByEmail().getId());
    }

    @Override
    public void cancel(Long id) {
        Appointment appointmentToCancel = this.appointmentRepository.findById(id).get();
        appointmentToCancel.setStatus("Cancelled");
        this.appointmentRepository.save(appointmentToCancel);
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
