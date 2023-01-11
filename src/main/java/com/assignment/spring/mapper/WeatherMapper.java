package com.assignment.spring.mapper;

import com.assignment.spring.dto.WeatherResponse;
import com.assignment.spring.orm.WeatherEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class WeatherMapper {

    public WeatherEntity mapper(WeatherResponse response) {
        WeatherEntity entity = new WeatherEntity();
        Optional.ofNullable(response.getId()).ifPresent(entity::setWeatherApiId);
        Optional.ofNullable(response.getName()).ifPresent(entity::setCity);
        Optional.ofNullable(response.getSys())
                .flatMap(sys -> Optional.ofNullable(sys.getCountry()))
                .ifPresent(entity::setCountry);
        Optional.ofNullable(response.getMain())
                .flatMap(main -> Optional.ofNullable(main.getTemp()))
                .ifPresent(entity::setTemperature);
        return entity;
    }
}
