package com.assignment.spring.service;

import com.assignment.spring.dto.Main;
import com.assignment.spring.dto.Sys;
import com.assignment.spring.dto.WeatherResponse;
import com.assignment.spring.dto.validation.OnCreate;
import com.assignment.spring.mapper.WeatherMapper;
import com.assignment.spring.orm.WeatherEntity;
import com.assignment.spring.orm.WeatherRepository;
import com.assignment.spring.services.impl.WeatherService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WeatherServiceTest {

    private static final Integer TEST_ID = 123;
    private static final String CITY_NAME = "name";
    private static final Double TEMP = 234.00;
    private static final String COUNTRY = "country";
    @Mock
    private WeatherMapper weatherMapper;
    @Mock
    private WeatherRepository weatherRepository;
    @InjectMocks
    private WeatherService uut;

    private WeatherResponse response;

    private WeatherEntity weatherEntity;
    @Spy
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

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

        weatherEntity = new WeatherEntity();
        weatherEntity.setCity(CITY_NAME);
        weatherEntity.setWeatherApiId(TEST_ID);
        weatherEntity.setTemperature(TEMP);
        weatherEntity.setCountry(COUNTRY);
    }

    @After
    public void clean() {
        validator = null;
    }

    @Test(expected = Exception.class)
    public void testException() throws Exception {
        when(weatherRepository.save(Mockito.any())).thenThrow(new Exception("e"));

        uut.insertEntity(new WeatherResponse());
    }

    @Test
    public void testInsert() throws Exception {
        when(weatherRepository.save(Mockito.any())).thenReturn(weatherEntity);
        when(weatherMapper.mapper(response)).thenReturn(weatherEntity);

        WeatherResponse test = uut.insertEntity(response);

        assertEquals(TEST_ID, test.getId());
        assertEquals(CITY_NAME, test.getName());
        assertEquals(COUNTRY, test.getSys().getCountry());
        assertEquals(TEMP, test.getMain().getTemp());
    }

    @Test
    public void idValidation_fail() {
        response.setId(null);
        Set<ConstraintViolation<WeatherResponse>> violationSet = validator.validate(response, OnCreate.class);

        assertFalse(violationSet.isEmpty());
    }
}