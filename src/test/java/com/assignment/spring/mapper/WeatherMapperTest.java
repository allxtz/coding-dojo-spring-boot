package com.assignment.spring.mapper;

import com.assignment.spring.dto.Main;
import com.assignment.spring.dto.Sys;
import com.assignment.spring.dto.WeatherResponse;
import com.assignment.spring.orm.WeatherEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class WeatherMapperTest {

    private static final Integer TEST_ID = 123;
    private static final String CITY_NAME = "name";
    private static final Double TEMP = 234.00;
    private static final String COUNTRY = "country";
    private WeatherResponse response;

    @InjectMocks
    private WeatherMapper uut;

    @Before
    public void init() {
        Sys sys = new Sys();
        sys.setCountry(COUNTRY);

        Main main = new Main();
        main.setTemp(TEMP);

        response = new WeatherResponse();
        response.setId(TEST_ID);
        response.setName(CITY_NAME);
        response.setSys(sys);
        response.setMain(main);
    }

    @Test
    public void convertWithAllFields() {
        WeatherEntity result = uut.mapper(response);

        assertEquals(TEST_ID, result.getWeatherApiId());
        assertEquals(CITY_NAME, result.getCity());
        assertEquals(TEMP, result.getTemperature());
        assertEquals(COUNTRY, result.getCountry());
    }
}
