package com.example.webexam.service.contracts;

import com.example.webexam.model.entity.City;

import java.util.List;

public interface CityService {

    boolean isCityListEmpty();

    void seedCities();

    City findByCityId(Long id);

    List<City> findAllCities();
}
