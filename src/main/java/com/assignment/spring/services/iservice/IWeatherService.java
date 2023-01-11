package com.assignment.spring.services.iservice;

import com.assignment.spring.dto.WeatherResponse;
import com.assignment.spring.dto.validation.OnCreate;
import org.springframework.validation.annotation.Validated;

public interface IWeatherService {
    WeatherResponse insertEntity(@Validated(OnCreate.class) WeatherResponse weatherResponse) throws Exception;
}
