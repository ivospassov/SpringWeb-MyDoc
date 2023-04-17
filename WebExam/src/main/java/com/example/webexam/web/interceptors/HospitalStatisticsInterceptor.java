package com.example.webexam.web.interceptors;

import com.example.webexam.model.entity.Hospital;
import com.example.webexam.service.contracts.HospitalService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class HospitalStatisticsInterceptor implements HandlerInterceptor {

    private final HospitalService hospitalService;

    public HospitalStatisticsInterceptor(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {

        //Exclude the part '/hospitals/' from the URI; get only the hospital ID (purpose: statistics)
        Long hospitalId = Long.parseLong(request.getRequestURI().substring(11));

        this.hospitalService.updateHospitalStatistics(hospitalId);
    }
}
