package com.example.webexam.web.interceptors;

import com.example.webexam.service.contracts.DoctorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class DoctorStatisticsInterceptor implements HandlerInterceptor {

    private final DoctorService doctorService;

    public DoctorStatisticsInterceptor(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {

        //Exclude the part '/review/comment/' from the URI; get only the doctor ID (purpose: statistics)
        Long doctorId = Long.parseLong(request.getRequestURI().substring(8));
        System.out.println(doctorId);

        this.doctorService.updateDoctorStatistics(doctorId);
    }
}
