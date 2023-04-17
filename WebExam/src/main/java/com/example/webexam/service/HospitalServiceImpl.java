package com.example.webexam.service;

import com.example.webexam.model.entity.Hospital;
import com.example.webexam.model.enums.HospitalUrls;
import com.example.webexam.model.enums.HospitalsName;
import com.example.webexam.repository.HospitalRepository;
import com.example.webexam.service.contracts.CityService;
import com.example.webexam.service.contracts.HospitalService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HospitalServiceImpl implements HospitalService {

    private final HospitalRepository hospitalRepository;
    private final CityService cityService;

    public HospitalServiceImpl(HospitalRepository hospitalRepository, CityService cityService) {
        this.hospitalRepository = hospitalRepository;
        this.cityService = cityService;
    }

    @Override
    public boolean isHospitalListEmpty() {
        return this.hospitalRepository.count() <= 0;
    }

    @Override
    public void seedHospitals() {
        if (isHospitalListEmpty()) {
            Hospital isul = new Hospital(HospitalsName.Isul.name(), cityService.findByCityId(1L));
            Hospital tokuda = new Hospital(HospitalsName.Tokuda.name(), cityService.findByCityId(2L));
            Hospital nadezhda = new Hospital(HospitalsName.Nadezhda.name(), cityService.findByCityId(3L));
            Hospital hope = new Hospital(HospitalsName.Hope.name(), cityService.findByCityId(4L));
            Hospital sofiamed = new Hospital(HospitalsName.SofiaMed.name(), cityService.findByCityId(1L));
            Hospital mayo = new Hospital(HospitalsName.Mayo.name(), cityService.findByCityId(5L));
            Hospital aleksandrovska = new Hospital(HospitalsName.Aleksandrovska.name(), cityService.findByCityId(2L));
            Hospital serdika = new Hospital(HospitalsName.Serdika.name(), cityService.findByCityId(3L));

            List<Hospital> hospitals = new ArrayList<>(List.of(isul, tokuda, nadezhda, hope, sofiamed, mayo, aleksandrovska, serdika));
            List<Hospital> modifiedHospitals = hospitals.stream().map(this::setImageUrl).toList();
            List<Hospital> hospitalsModified = modifiedHospitals.stream().map(this::setUserSearchCount).toList();
            this.hospitalRepository.saveAll(hospitalsModified);
        }
    }

    private Hospital setUserSearchCount(Hospital hospital) {
        hospital.setUserSearchCount(0L);
        return hospital;
    }

    private Hospital setImageUrl(Hospital hospital) {

        switch (hospital.getName().toLowerCase()) {
            case "sofiamed" -> hospital.setImage(HospitalUrls.SOFIAMED_URL.getHospitalUrl());
            case "mayo" -> hospital.setImage(HospitalUrls.MAYO_URL.getHospitalUrl());
            case "aleksandrovska" -> hospital.setImage(HospitalUrls.ALEKSANDROVSKA_URL.getHospitalUrl());
            case "serdika" -> hospital.setImage(HospitalUrls.SERDIKA_URL.getHospitalUrl());
            case "tokuda" -> hospital.setImage(HospitalUrls.TOKUDA_URL.getHospitalUrl());
            case "isul" -> hospital.setImage(HospitalUrls.ISUL_URL.getHospitalUrl());
            case "nadezhda" -> hospital.setImage(HospitalUrls.NADEZHDA_URL.getHospitalUrl());
            case "hope" -> hospital.setImage(HospitalUrls.HOPE_URL.getHospitalUrl());
        }

        return hospital;
    }

    @Override
    public Hospital findById(Long id) {
        return this.hospitalRepository.findById(id).get();
    }

    @Override
    public Optional<Hospital> findByName(String name) {
        return this.hospitalRepository.findByName(name);
    }

    @Override
    public List<Hospital> findAllHospitals() {
        return hospitalRepository.findAll();
    }

    @Override
    public List<Hospital> findByCity(String cityName) {
        return this.hospitalRepository.findByCityName(cityName);
    }

    @Override
    public void updateHospitalStatistics(Long hospitalId) {
        Hospital hospital = findById(hospitalId);
        Long currentUserSearchCount = hospital.getUserSearchCount();

        //Update search count for specific hospitals
        hospital.setUserSearchCount(currentUserSearchCount + 1);
        this.hospitalRepository.save(hospital);
    }
}
