package com.example.webexam.service.contracts;

import com.example.webexam.model.dtos.AppointmentDTO;
import com.example.webexam.model.entity.Appointment;
import com.example.webexam.model.entity.Doctor;

import java.util.List;

public interface AppointmentService {

    void seedAppointment(AppointmentDTO appointmentDTO, Doctor doctor);

    List<Appointment> getByPatientId();

    void cancel(Long id);
}
