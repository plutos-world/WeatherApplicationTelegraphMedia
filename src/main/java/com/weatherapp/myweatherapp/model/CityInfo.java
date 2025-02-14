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

    /**
    * Gets the time of sunrise for the city.
    * @return The sunrise time as a String (e.g., "06:30").
    */
    public String getSunrise() {
      return sunrise;
    }
    /**
    * Gets the time of sunset for the city.
    * @return The sunset time as a String (e.g., "18:45").
    */
    public String getSunset() {
      return sunset;
    }
    /**
    * Gets the "feels like" temperature,
    * @return The "feels like" temperature as a String.
    */
    public String getFeelslike() {
      return feelslike;
    }
    /**
    * Gets the humidity level in percentage.
    * @return The humidity as a String .
    */
    public String getHumidity() {
      return humidity;
    }
    /**
    * Gets the current weather conditions .
    * @return A brief description of the weather conditions.
    */
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
