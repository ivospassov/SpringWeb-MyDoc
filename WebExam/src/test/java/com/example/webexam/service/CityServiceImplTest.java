package com.example.webexam.service;

import com.example.webexam.model.entity.City;
import com.example.webexam.repository.CityRepository;
import com.example.webexam.service.contracts.CityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CityServiceImplTest {

    private static final Long NON_EXISTING_ID = 99L;

    @Mock
    private CityService cityService;

    @Mock
    private CityRepository cityRepository;

    @Test
    void testCityRepositoryIsEmpty() {

        when(cityService.isCityListEmpty())
                .thenReturn(true);

        assertTrue(this.cityService.isCityListEmpty());
        assertEquals(0, cityRepository.count());
    }

    @Test
    void testCityRepositoryIsNotEmpty() {

        City city = new City();
        city.setId(1L);
        city.setName("Sofia");

        when(cityRepository.save(any(City.class)))
                .thenReturn(city);
        when(cityService.isCityListEmpty())
                .thenReturn(false);

        cityRepository.save(city);
        assertFalse(cityService.isCityListEmpty());
    }

    @Test
    void testReturnsCityById() {
        City cityDummy = new City();
        cityDummy.setName("Dummy");
        cityDummy.setId(1L);

        when(cityRepository.save(any(City.class)))
                .thenReturn(cityDummy);
        when(cityService.findByCityId(1L))
                .thenReturn(cityDummy);

        cityRepository.save(cityDummy);

        assertEquals(cityDummy.getName(), cityService.findByCityId(1L).getName());
    }

    @Test
    void testFindByCityIdReturnsNull() {
        assertNull(cityService.findByCityId(NON_EXISTING_ID));
    }

    @Test
    void testFindAllCities() {
        City sofia = new City();
        sofia.setId(1L);
        sofia.setName("Sofia");

        City plovdiv = new City();
        plovdiv.setId(2L);
        plovdiv.setName("Plovdiv");

        City velikoTurnovo = new City();
        velikoTurnovo.setId(3L);
        velikoTurnovo.setName("Veliko Turnovo");

        when(cityRepository.save(any(City.class)))
                .thenReturn(sofia, plovdiv, velikoTurnovo);
        when(cityService.findAllCities())
                .thenReturn(List.of(sofia, plovdiv, velikoTurnovo));

        cityRepository.save(sofia);
        cityRepository.save(plovdiv);
        cityRepository.save(velikoTurnovo);

        List<City> actualCityList = cityService.findAllCities();
        assertEquals(3, actualCityList.size());
        assertEquals("Plovdiv", actualCityList.get(1).getName());
    }
}
