package com.example.webexam.service;

import com.example.webexam.model.entity.Doctor;
import com.example.webexam.model.entity.Hospital;
import com.example.webexam.model.enums.SpecialtyType;
import com.example.webexam.repository.DoctorRepository;
import com.example.webexam.service.contracts.DoctorService;
import com.example.webexam.service.contracts.ExtractDoctorInfoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    private static final String FIRST_DOCTOR = "https://media.istockphoto.com/id/1189304032/photo/doctor-holding-digital-tablet-at-meeting-room.jpg?s=612x612&w=0&k=20&c=RtQn8w_vhzGYbflSa1B5ea9Ji70O8wHpSgGBSh0anUg=";
    private static final String SECOND_DOCTOR = "https://st4.depositphotos.com/1325771/39154/i/600/depositphotos_391545206-stock-photo-happy-male-medical-doctor-portrait.jpg";
    private static final String THIRD_DOCTOR = "https://s3-eu-west-1.amazonaws.com/intercare-web-public/wysiwyg-uploads%2F1580196666465-doctor.jpg";
    private static final String FOURTH_DOCTOR = "https://www.yourfreecareertest.com/wp-content/uploads/2018/01/how_to_become_a_doctor.jpg";
    private static final String FIFTH_DOCTOR = "https://st2.depositphotos.com/3889193/8015/i/600/depositphotos_80150956-stock-photo-confident-female-doctor-at-office.jpg";
    private static final String SIXTH_DOCTOR = "https://thevideoink.com/wp-content/uploads/2021/11/wysiwyg-uploads_1569586526901-doctor.jpg";
    private static final String SEVENTH_DOCTOR = "https://st2.depositphotos.com/3889193/8015/i/600/depositphotos_80150956-stock-photo-confident-female-doctor-at-office.jpg";
    private static final String EIGHT_DOCTOR = "https://medimap.ca/wp-content/uploads/2022/10/iStock-1193303828.jpg";
    private static final String NINTH_DOCTOR = "https://www.waterfieldhousepractice.nhs.uk/media/content/images/doctor.jpg";
    private static final String TENTH_DOCTOR = "https://www.eatthis.com/wp-content/uploads/sites/4/2020/12/serious-doctor-hospital.jpg?quality=82&strip=1";

    private final DoctorRepository doctorRepository;
    private final ExtractDoctorInfoService doctorInfo;

    public DoctorServiceImpl(DoctorRepository doctorRepository, ExtractDoctorInfoService doctorInfo) {
        this.doctorRepository = doctorRepository;
        this.doctorInfo = doctorInfo;
    }

    @Override
    public boolean isDoctorListEmpty() {
        return this.doctorRepository.count() <= 0;
    }

    @Override
    public void seedDoctors() {
        if (isDoctorListEmpty()) {
            this.doctorRepository.saveAll(this.doctorInfo.getAllDoctors());
        }
    }

    @Override
    public List<Doctor> findAllDoctors() {
        return this.doctorRepository.findAll();
    }

    @Override
    public Optional<Doctor> findDoctorById(Long id) {
        return this.doctorRepository.findById(id);
    }

    @Override
    public List<Doctor> findByCityNameAndSpecialtyName(String city, SpecialtyType specialtyType) {
        return this.doctorRepository.findByCityNameAndSpecialtyName(city, specialtyType);
    }

    @Override
    public List<Doctor> findDoctorsByHospital(Hospital hospital) {
        return this.doctorRepository.findAllByHospital(hospital);
    }

    @Override
    public void updateDoctorStatistics(Long doctorId) {
        Doctor doctor = this.doctorRepository.findById(doctorId).get();
        Long currentVisitations = doctor.getVisitationsCount();

        doctor.setVisitationsCount(currentVisitations + 1L);
        this.doctorRepository.save(doctor);
    }
}
