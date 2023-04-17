package com.example.webexam.config;

import com.example.webexam.service.contracts.DoctorService;
import com.example.webexam.service.contracts.HospitalService;
import com.example.webexam.web.interceptors.HospitalStatisticsInterceptor;
import com.example.webexam.web.interceptors.DoctorStatisticsInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfigurer implements WebMvcConfigurer {

    private final HospitalService hospitalService;
    private final DoctorService doctorService;

    public MvcConfigurer(HospitalService hospitalService, DoctorService doctorService) {
        this.hospitalService = hospitalService;
        this.doctorService = doctorService;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HospitalStatisticsInterceptor(hospitalService)).addPathPatterns("/hospitals/*");
        registry.addInterceptor(new DoctorStatisticsInterceptor(doctorService)).addPathPatterns("/review/*");
    }
}
