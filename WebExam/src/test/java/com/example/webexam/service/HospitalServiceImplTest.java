package com.example.webexam.service;

import com.example.webexam.model.entity.City;
import com.example.webexam.model.entity.Hospital;
import com.example.webexam.repository.CityRepository;
import com.example.webexam.repository.HospitalRepository;
import com.example.webexam.service.contracts.HospitalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HospitalServiceImplTest {

    @Mock
    private HospitalRepository hospitalRepository;

    @Mock
    private CityRepository cityRepository;

    @Mock
    private HospitalService hospitalService;

    @Test
    void testHospitalRepositoryIsNotEmpty() {
        City city = new City();
        city.setName("Sofia");
        city.setId(1L);

        Hospital hospital = new Hospital();
        hospital.setName("Nadezhda");
        hospital.setCity(city);

        when(cityRepository.save(any(City.class)))
                .thenReturn(city);
        when(hospitalRepository.save(hospital))
                .thenReturn(hospital);
        when(hospitalService.isHospitalListEmpty())
                .thenReturn(false);

        cityRepository.save(city);
        hospitalRepository.save(hospital);

        assertFalse(hospitalService.isHospitalListEmpty());
    }

    @Test
    void testHospitalRepositoryIsEmpty() {
        when(hospitalService.isHospitalListEmpty())
                .thenReturn(true);

        assertTrue(hospitalService.isHospitalListEmpty());
    }

    @Test
    void testFindByIdReturnsHospital() {
        City city = new City();
        city.setName("Sofia");
        city.setId(1L);

        Hospital hospital = new Hospital();
        hospital.setName("Hope");
        hospital.setCity(city);
        hospital.setId(1L);

        when(cityRepository.save(any(City.class)))
                .thenReturn(city);
        when(hospitalRepository.save(any(Hospital.class)))
                .thenReturn(hospital);
        when(hospitalService.findById(1L))
                .thenReturn(hospital);

        cityRepository.save(city);
        hospitalRepository.save(hospital);

        assertEquals("Hope", hospitalService.findById(1L).getName());
    }

    @Test
    void testFindByIdReturnsNull() {
        when(hospitalService.findById(any(Long.class)))
                .thenReturn(null);

        assertNull(hospitalService.findById(55L));
    }

    @Test
    void testFindByNameReturnsHospital() {
        City city = new City();
        city.setName("Sofia");
        city.setId(1L);

        Hospital hospital = new Hospital();
        hospital.setName("Hope");
        hospital.setCity(city);
        hospital.setId(1L);

        when(cityRepository.save(any(City.class)))
                .thenReturn(city);
        when(hospitalRepository.save(any(Hospital.class)))
                .thenReturn(hospital);
        when(hospitalService.findByName(any(String.class)))
                .thenReturn(Optional.of(hospital));

        cityRepository.save(city);
        hospitalRepository.save(hospital);

        assertTrue(hospitalService.findByName("Hope").isPresent());
        assertEquals("Hope", hospitalService.findByName("Hope").get().getName());
    }

    @Test
    void testFindByNameReturnsNull() {
        when(hospitalService.findByName(any(String.class)))
                .thenReturn(Optional.empty());

        assertFalse(hospitalService.findByName("Hope").isPresent());
    }

    @Test
    void testFindAllHospitals() {
        City city = new City();
        city.setName("Sofia");
        city.setId(1L);

        Hospital hope = new Hospital();
        hope.setName("Hope");
        hope.setCity(city);
        hope.setId(1L);

        Hospital tokuda = new Hospital();
        tokuda.setName("Tokuda");
        tokuda.setCity(city);
        tokuda.setId(2L);

        when(cityRepository.save(any(City.class)))
                .thenReturn(city);
        when(hospitalRepository.save(hope))
                .thenReturn(hope);
        when(hospitalRepository.save(tokuda))
                .thenReturn(tokuda);
        when(hospitalService.findAllHospitals())
                .thenReturn(List.of(hope, tokuda));

        cityRepository.save(city);
        hospitalRepository.save(hope);
        hospitalRepository.save(tokuda);

        List<Hospital> hospitalList = hospitalService.findAllHospitals();
        assertEquals(2, hospitalList.size());
        assertEquals(1L, hospitalList.get(0).getId());
    }

    @Test
    void testFindByCityReturnsHospital() {
        City city = new City();
        city.setId(1L);
        city.setName("Sofia");

        Hospital hospital = new Hospital();
        hospital.setId(1L);
        hospital.setName("Hope");
        hospital.setCity(city);

        when(cityRepository.save(any(City.class)))
                .thenReturn(city);
        when(hospitalRepository.save(any(Hospital.class)))
                .thenReturn(hospital);
        when(hospitalService.findByName(any(String.class)))
                .thenReturn(Optional.of(hospital));

        cityRepository.save(city);
        hospitalRepository.save(hospital);

        Optional<Hospital> hope = hospitalService.findByName("Hope");
        assertTrue(hope.isPresent());
        assertEquals("Sofia", hope.get().getCity().getName());
    }

    @Test
    void testFindByCityReturnsNull() {
        when(hospitalService.findByName(any(String.class)))
                .thenReturn(Optional.empty());

        Optional<Hospital> nonExisting = hospitalService.findByName("non-existing");
        assertFalse(nonExisting.isPresent());
    }

    @Test
    void testSetImageUrlIsCorrect() {
        String imageUrlDummy = "https://dummy-image-url.com";

        City city = new City();
        city.setId(1L);
        city.setName("Sofia");

        Hospital hospital = new Hospital();
        hospital.setId(1L);
        hospital.setName("Hope");
        hospital.setImage(imageUrlDummy);

        when(cityRepository.save(any(City.class)))
                .thenReturn(city);
        when(hospitalRepository.save(any(Hospital.class)))
                .thenReturn(hospital);

        cityRepository.save(city);
        Hospital persistedHospital = hospitalRepository.save(hospital);

        assertEquals(imageUrlDummy, persistedHospital.getImage());
    }
}
