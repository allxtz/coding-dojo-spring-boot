package com.assignment.spring.controllers;

import com.assignment.spring.dto.WeatherResponse;
import com.assignment.spring.services.iservice.IWeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.Pattern;
import java.util.Optional;

@RestController()
@Validated
@RequestMapping("/api/v1")
@Tag(name = "Weather operations")
public class WeatherController {

    private final RestTemplate restTemplate;
    private final IWeatherService weatherService;
    private final String restUrl;

    public WeatherController(IWeatherService weatherService, RestTemplate restTemplate, @Value("${rest.api.url}") String url) {
        this.weatherService = weatherService;
        this.restTemplate = restTemplate;
        this.restUrl = url;
    }

    @PostMapping("/weather/{city}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "get weather for city")
    public WeatherResponse weather(@Pattern(regexp = "^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$", message = "city doesn't have a valid format")
                                   @PathVariable String city) throws Exception {

        String url = restUrl.replace("{city}", city);
        WeatherResponse response = Optional.ofNullable(restTemplate.getForEntity(url, WeatherResponse.class))
                .flatMap(weatherResponseResponseEntity -> Optional.ofNullable(weatherResponseResponseEntity.getBody()))
                .orElse(new WeatherResponse());
        return weatherService.insertEntity(response);
    }
}