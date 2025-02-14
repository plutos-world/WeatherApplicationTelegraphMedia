package com.weatherapp.myweatherapp.controller;

import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class WeatherController {

  @Autowired
  WeatherService weatherService;

  @GetMapping("/forecast/{city}")
  public ResponseEntity<CityInfo> forecastByCity(@PathVariable("city") String city) {

    CityInfo ci = weatherService.forecastByCity(city);

    return ResponseEntity.ok(ci);
  }  

     // Compare daylight hours between two cities
  @GetMapping("/compare-daylight")
  public String compareDaylight(@RequestParam String city1, @RequestParam String city2) {
  CityInfo cityInfo1 = weatherService.forecastByCity(city1);
  CityInfo cityInfo2 = weatherService.forecastByCity(city2);
 
    if (cityInfo1 == null || cityInfo2 == null || 
       cityInfo1.getCurrentConditions() == null || cityInfo2.getCurrentConditions() == null) {
       return "Error: Could not retrieve weather data for one or both cities.";
     }
 
  long daylight1 = calculateDaylight(cityInfo1); //ToDo Create Calculatedaylight function!
  long daylight2 = calculateDaylight(cityInfo2); // 
  return (daylight1 > daylight2) ? city1 + " has longer daylight hours." : city2 + " has longer daylight hours.";
     }
     
     @GetMapping("/rain-check")
     public String checkRain(@RequestParam String city1, @RequestParam String city2) {
         CityInfo cityInfo1 = weatherService.forecastByCity(city1);
         CityInfo cityInfo2 = weatherService.forecastByCity(city2);
 
         if (cityInfo1 == null || cityInfo2 == null || 
             cityInfo1.getCurrentConditions() == null || cityInfo2.getCurrentConditions() == null) {
             return "Error: Could not retrieve weather data for one or both cities.";
         }
 
         boolean isRaining1 = cityInfo1.getCurrentConditions().getConditions().toLowerCase().contains("rain");
         boolean isRaining2 = cityInfo2.getCurrentConditions().getConditions().toLowerCase().contains("rain");
 
         if (isRaining1 && isRaining2) return "It is raining in both cities.";
         if (isRaining1) return "It is raining in " + city1;
         if (isRaining2) return "It is raining in " + city2;
 
         return "It is not raining in either city.";
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
 *
 */



