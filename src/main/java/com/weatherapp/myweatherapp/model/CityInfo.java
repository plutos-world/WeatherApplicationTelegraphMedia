package com.weatherapp.myweatherapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class CityInfo {

  @JsonProperty("address")
  String address;

  @JsonProperty("description")
  String description;

  @JsonProperty("currentConditions")
  CurrentConditions currentConditions;

  //getter for CurrentConditions
  public CurrentConditions getCurrentConditions() {
    return currentConditions;
  } 

  @JsonProperty("days")
  List<Days> days;

  static class CurrentConditions {
    @JsonProperty("temp")
    String currentTemperature;

    @JsonProperty("sunrise")
    String sunrise;

    @JsonProperty("sunset")
    String sunset;

    @JsonProperty("feelslike")
    String feelslike;

    @JsonProperty("humidity")
    String humidity;

    @JsonProperty("conditions")
    String conditions;
     
  //Gets the current conditions for the city
    public String getCurrentTemperature() {
      return currentTemperature;
  }
    //Gets the time of sunrise for the city.
    public String getSunrise() {
      return sunrise;
    }
    //Gets the time of sunset for the city.
    public String getSunset() {
      return sunset;
    }
    //Gets the "feels like" temperature,
    public String getFeelslike() {
      return feelslike;
    }
    //Gets the humidity level in percentage.
    public String getHumidity() {
      return humidity;
    }
    //Gets the current weather conditions .
    public String getConditions() {
      return conditions;
    }
  }
  static class Days {

    @JsonProperty("datetime")
    String date;

    @JsonProperty("temp")
    String currentTemperature;

    @JsonProperty("tempmax")
    String maxTemperature;

    @JsonProperty("tempmin")
    String minTemperature;

    @JsonProperty("conditions")
    String conditions;

    @JsonProperty("description")
    String description;
  }
}
