package com.example.webexam.service;

import com.example.webexam.model.entity.City;
import com.example.webexam.model.entity.Doctor;
import com.example.webexam.model.entity.Hospital;
import com.example.webexam.model.entity.Specialty;
import com.example.webexam.model.enums.DoctorBiography;
import com.example.webexam.model.enums.DoctorImageURL;
import com.example.webexam.model.enums.SpecialtyType;
import com.example.webexam.repository.CityRepository;
import com.example.webexam.repository.HospitalRepository;
import com.example.webexam.service.contracts.ExtractDoctorInfoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExtractDoctorInfoServiceImplTest {

    @Mock
    private ExtractDoctorInfoService extractDoctorInfoService;

    @Mock
    private CityRepository cityRepository;

    @Mock
    private HospitalRepository hospitalRepository;

    @Test
    void testExtractDoctorInfo() {

        City city = new City();
        city.setId(1L);
        city.setName("Sofia");

        Hospital hospital = new Hospital();
        hospital.setId(1L);
        hospital.setName("Hope");
        hospital.setCity(city);

        Specialty specialty = new Specialty();
        specialty.setId(1L);
        specialty.setName(SpecialtyType.Dermatology);

        Doctor first = new Doctor("Doctor", "Dummy", 29,
                specialty, DoctorBiography.IMMUNOLOGY_MASTURBAKIS.getBiographyText(), "doctorDummy@mydoc.com",
                city, hospital, DoctorImageURL.MASTURBAKIS_URL.getImageUrl(), 0L);


        Doctor second = new Doctor("Someone", "new", 29,
                specialty, DoctorBiography.IMMUNOLOGY_MASTURBAKIS.getBiographyText(), "someoneNew@mydoc.com",
                city, hospital, DoctorImageURL.MASTURBAKIS_URL.getImageUrl(), 0L);

        when(extractDoctorInfoService.getAllDoctors())
                .thenReturn(List.of(first, second));

        List<Doctor> doctorList = extractDoctorInfoService.getAllDoctors();
        assertEquals(2, doctorList.size());
    }
}