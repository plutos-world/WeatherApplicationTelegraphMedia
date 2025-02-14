package com.weatherapp.myweatherapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class CityInfo {

  @JsonProperty("address")
  private String address;

  @JsonProperty("description")
  private String description;

  @JsonProperty("currentConditions")
  private CurrentConditions currentConditions;

  @JsonProperty("days")
  private List<Days> days;

  // Getter for currentConditions
  public CurrentConditions getCurrentConditions() {
      return currentConditions;
  }

  // Getter for days
  public List<Days> getDays() {
      return days;
  }

  public static class CurrentConditions {
      @JsonProperty("temp")
      private String currentTemperature;

      @JsonProperty("sunrise")
      private String sunrise;

      @JsonProperty("sunset")
      private String sunset;

      @JsonProperty("feelslike")
      private String feelslike;

      @JsonProperty("humidity")
      private String humidity;

      @JsonProperty("conditions")
      private String conditions;

      // Add getters
      public String getCurrentTemperature() {
          return currentTemperature;
      }

      public String getSunrise() {
          return sunrise;
      }

      public String getSunset() {
          return sunset;
      }

      public String getFeelslike() {
          return feelslike;
      }

      public String getHumidity() {
          return humidity;
      }

      public String getConditions() {
          return conditions;
      }
  }

  public static class Days {
      @JsonProperty("datetime")
      private String date;

      @JsonProperty("temp")
      private String currentTemperature;

      @JsonProperty("tempmax")
      private String maxTemperature;

      @JsonProperty("tempmin")
      private String minTemperature;

      @JsonProperty("conditions")
      private String conditions;

      @JsonProperty("description")
      private String description;

      // Add getters
      public String getDate() {
          return date;
      }

      public String getCurrentTemperature() {
          return currentTemperature;
      }

      public String getMaxTemperature() {
          return maxTemperature;
      }

      public String getMinTemperature() {
          return minTemperature;
      }

      public String getConditions() {
          return conditions;
      }

      public String getDescription() {
          return description;
      }
  }
}
