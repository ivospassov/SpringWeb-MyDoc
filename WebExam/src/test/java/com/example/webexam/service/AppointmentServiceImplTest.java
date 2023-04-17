package com.example.webexam.service;

import com.example.webexam.model.dtos.AppointmentDTO;
import com.example.webexam.model.entity.*;
import com.example.webexam.repository.AppointmentRepository;
import com.example.webexam.service.contracts.AppointmentService;
import com.example.webexam.service.contracts.DoctorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceImplTest {

    @Mock
    private AppointmentService appointmentService;

    @Mock
    private AppointmentRepository appointmentRepository;

    @Test
    void testSeedAppointment() {
        City city = new City();
        city.setId(1L);
        city.setName("Sofia");

        Hospital hospital = new Hospital();
        hospital.setId(1L);
        hospital.setName("Hope");

        UserEntity patient = new UserEntity();
        patient.setId(1L);
        patient.setEmail("example@example.com");
        patient.setPassword("dummy");
        patient.setFirstName("Dummy");
        patient.setLastName("Dummy");

        Doctor doctor = new Doctor();
        doctor.setId(1L);
        doctor.setFirstName("Dr.");
        doctor.setLastName("Dummy");
        doctor.setAge(45);
        doctor.setEmail("doctor@example.com");
        doctor.setVisitationsCount(0L);
        doctor.setHospital(hospital);

        AppointmentDTO appointment = new AppointmentDTO();
        appointment.setPurposeOfVisit("Primary Examination");
        appointment.setPreferredDate("01/05/2023");
        appointment.setPreferredTime("09:00");

        doNothing().when(appointmentService).seedAppointment(any(AppointmentDTO.class), any(Doctor.class));

        appointmentService.seedAppointment(appointment, doctor);
    }

    @Test
    void testGetByPatientId() {

        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setFirstName("Dummy");
        user.setLastName("Dummy");
        user.setEmail("example@example.com");
        user.setPassword("secret");

        Appointment appointment = new Appointment();
        appointment.setPatient(user);
        appointment.setStatus("Going");
        appointment.setPurposeOfVisit("Primary Examination");

        when(appointmentService.getByPatientId())
                .thenReturn(List.of(appointment));

        List<Appointment> appointments = appointmentService.getByPatientId();
        assertEquals(1, appointments.size());
        assertEquals("Going", appointments.get(0).getStatus());
    }
}
