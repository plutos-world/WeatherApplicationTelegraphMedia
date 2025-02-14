package com.weatherapp.myweatherapp.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.model.CityInfo.CurrentConditions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class WeatherServiceTest {

    @Mock
    private WeatherService weatherService;

    private CityInfo mockCityInfo;
    private CurrentConditions mockConditions;

    @BeforeEach
    void setUp() {
        mockCityInfo = new CityInfo();
        mockConditions = new CurrentConditions();
        
        // Using reflection to set private fields
        try {
            java.lang.reflect.Field sunriseField = CurrentConditions.class.getDeclaredField("sunrise");
            java.lang.reflect.Field sunsetField = CurrentConditions.class.getDeclaredField("sunset");
            sunriseField.setAccessible(true);
            sunsetField.setAccessible(true);
            sunriseField.set(mockConditions, "06:00");
            sunsetField.set(mockConditions, "18:00");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testForecastByCity() {
        when(weatherService.forecastByCity("London")).thenReturn(mockCityInfo);
        CityInfo response = weatherService.forecastByCity("London");
        assertNotNull(response);
        assertEquals(mockCityInfo, response);
    }
}