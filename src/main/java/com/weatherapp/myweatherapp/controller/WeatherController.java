package com.weatherapp.myweatherapp.controller;

import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WeatherController {

  @Autowired
  WeatherService weatherService;

  @GetMapping("/forecast/{city}")
  public ResponseEntity<CityInfo> forecastByCity(@PathVariable("city") String city) {

    CityInfo ci = weatherService.forecastByCity(city);

    return ResponseEntity.ok(ci);
  }  
}

/* Task 
 * --Daylight Hours Comparison--
 * 1)Retrive Sunrise and sunset times for both cities
 * 2)Calculate daylight duration
 * 3)return the city with the longest daylight 
 * -- Rain check --
 * 1)check current weather conditions for both cities 
 * 2)determine if its raining in either city 
 * 3)return the city where its currently raining 
 */



