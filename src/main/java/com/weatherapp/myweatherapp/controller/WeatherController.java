package com.weatherapp.myweatherapp.controller;

import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// This class is a REST controller that handles weather-related API requests
@RestController
@RequestMapping("/weather") // Base URL path for all endpoints in this controller
public class WeatherController {

  // Injects the WeatherService dependency for fetching weather data
  @Autowired
  WeatherService weatherService;
  
  /**
     * Gets the weather forecast for a specific city.
     * @param city The name of the city to fetch the forecast for.
     * @return CityInfo object containing weather details of the specified city.
     */
  @GetMapping("/forecast/{city}")
  public CityInfo forecastByCity(@PathVariable("city") String city) {
      return weatherService.forecastByCity(city);
  }
   /**
     * Compares the length of daylight hours between two cities.
     * @param city1 The first city.
     * @param city2 The second city.
     * @return A message indicating which city has longer daylight hours.
     */
  @GetMapping("/compare-daylight") // Maps GET requests to "/weather/compare-daylight"
  public String compareDaylight(@RequestParam String city1, @RequestParam String city2) {
     // Fetch weather data for both cities
      CityInfo cityInfo1 = weatherService.forecastByCity(city1);
      CityInfo cityInfo2 = weatherService.forecastByCity(city2);

     // Check if data retrieval was successful
      if (cityInfo1 == null || cityInfo2 == null || 
          cityInfo1.getCurrentConditions() == null || cityInfo2.getCurrentConditions() == null) {
          return "Error: Could not retrieve weather data for one or both cities.";
      }
      // Calculate daylight duration for each city
      long daylight1 = calculateDaylight(cityInfo1); 
      long daylight2 = calculateDaylight(cityInfo2); 
       // Determine and return which city has longer daylight hours
      return (daylight1 > daylight2) ? city1 + " has longer daylight hours." : city2 + " has longer daylight hours.";
  }
   /**
     * Checks whether it is currently raining in either of the two given cities.
     * @param city1 The first city.
     * @param city2 The second city.
     * @return A message indicating where it is raining.
     */
  @GetMapping("/rain-check") // Maps GET requests to "/weather/rain-check"
  public String checkRain(@RequestParam String city1, @RequestParam String city2) {
    // Fetch weather data for both cities
      CityInfo cityInfo1 = weatherService.forecastByCity(city1);
      CityInfo cityInfo2 = weatherService.forecastByCity(city2);
      // Check if data retrieval was successful
      if (cityInfo1 == null || cityInfo2 == null || 
          cityInfo1.getCurrentConditions() == null || cityInfo2.getCurrentConditions() == null) {
          return "Error: Could not retrieve weather data for one or both cities.";
      }
      // Check if it is raining in each city
      boolean isRaining1 = cityInfo1.getCurrentConditions().getConditions().toLowerCase().contains("rain");
      boolean isRaining2 = cityInfo2.getCurrentConditions().getConditions().toLowerCase().contains("rain");

      // Return appropriate message based on weather conditions
      if (isRaining1 && isRaining2) return "It is raining in both cities.";
      if (isRaining1) return "It is raining in " + city1;
      if (isRaining2) return "It is raining in " + city2;

      return "It is not raining in either city.";
  }
    /**
     * Calculates the duration of daylight in minutes for a given city.
     * @param cityInfo The CityInfo object containing sunrise and sunset times.
     * @return The total minutes of daylight.
     */
  private long calculateDaylight(CityInfo cityInfo) {
      if (cityInfo.getCurrentConditions() == null) {
          return 0;
      }

      String sunrise = cityInfo.getCurrentConditions().getSunrise();
      String sunset = cityInfo.getCurrentConditions().getSunset();

      if (sunrise == null || sunset == null) {
          return 0;
      }
      // Convert sunrise and sunset times to minutes and calculate daylight duration
      return convertToMinutes(sunset) - convertToMinutes(sunrise);
  }
  /**
     * Converts a time string (HH:mm) to total minutes from midnight.
     * @param time The time string in "HH:mm" format.
     * @return The total number of minutes from midnight.
     */
  private long convertToMinutes(String time) {
      if (time == null || !time.contains(":")) {
          return 0;
      }
      // Split time string into hours and minutes
      String[] parts = time.split(":");
      return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
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



