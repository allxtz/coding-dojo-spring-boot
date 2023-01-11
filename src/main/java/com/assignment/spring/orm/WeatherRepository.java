package com.assignment.spring.orm;

import org.springframework.data.repository.CrudRepository;

public interface WeatherRepository extends CrudRepository<WeatherEntity, Integer> {
}
