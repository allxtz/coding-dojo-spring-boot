package com.assignment.spring.services.impl;

import com.assignment.spring.dto.WeatherResponse;
import com.assignment.spring.dto.validation.OnCreate;
import com.assignment.spring.mapper.WeatherMapper;
import com.assignment.spring.orm.WeatherEntity;
import com.assignment.spring.orm.WeatherRepository;
import com.assignment.spring.services.iservice.IWeatherService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * @author devteam
 * @version v1.0
 * @since 1.0
 */
@Service
public class WeatherService implements IWeatherService {

    private final WeatherMapper weatherMapper;
    private final WeatherRepository weatherRepository;

    public WeatherService(WeatherMapper weatherMapper, WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
        this.weatherMapper = weatherMapper;
    }

    @Override
    public WeatherResponse insertEntity(@Validated(OnCreate.class) WeatherResponse weatherResponse) throws Exception {
        WeatherEntity weatherEntity = weatherMapper.mapper(weatherResponse);
        try {
            weatherRepository.save(weatherEntity);
        } catch (Exception e) {
            throw new Exception("Could not save: {}", e.getCause());
        }
        return weatherResponse;
    }
}