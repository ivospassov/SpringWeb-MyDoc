package com.example.webexam.service;

import com.example.webexam.model.entity.City;
import com.example.webexam.model.entity.Specialty;
import com.example.webexam.model.enums.SpecialtyType;
import com.example.webexam.repository.CityRepository;
import com.example.webexam.repository.SpecialtyRepository;
import com.example.webexam.service.contracts.CityService;
import com.example.webexam.service.contracts.SpecialtyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SpecialtyServiceImplTest {

    @Mock
    private SpecialtyService specialtyService;

    @Mock
    private SpecialtyRepository specialtyRepository;

    @Test
    void testCityRepositoryIsEmpty() {

        when(specialtyService.isSpecialtyListEmpty())
                .thenReturn(true);

        assertTrue(specialtyService.isSpecialtyListEmpty());
        assertEquals(0, specialtyRepository.count());
    }

    @Test
    void testCityRepositoryIsNotEmpty() {

        Specialty specialty = new Specialty();
        specialty.setId(1L);
        specialty.setName(SpecialtyType.Pediatrics);

        when(specialtyRepository.save(any(Specialty.class)))
                .thenReturn(specialty);
        when(specialtyService.isSpecialtyListEmpty())
                .thenReturn(false);

        specialtyRepository.save(specialty);
        assertFalse(specialtyService.isSpecialtyListEmpty());
    }

    @Test
    void testFindAllSpecialties() {

        Specialty specialty = new Specialty();
        specialty.setId(1L);
        specialty.setName(SpecialtyType.Pediatrics);

        when(specialtyRepository.save(any(Specialty.class)))
                .thenReturn(specialty);
        when(specialtyService.findAll())
                .thenReturn(List.of(specialty));

        specialtyRepository.save(specialty);

        List<Specialty> actualSpecialtyList = specialtyService.findAll();
        assertEquals(1, actualSpecialtyList.size());
        assertEquals("Pediatrics", actualSpecialtyList.get(0).getName().name());
    }
}