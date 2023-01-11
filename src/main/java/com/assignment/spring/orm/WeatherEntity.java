package com.assignment.spring.orm;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "weather")
public class WeatherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull(message = "city is null")
    private String city;
    private String country;
    private Double temperature;
    @Column(name = "weather_api_id")
    private Integer weatherApiId;
}
