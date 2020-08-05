package com.example.WindsurferWeather;

import com.example.WindsurferWeather.Dto.LocationForecast;
import com.example.WindsurferWeather.Dto.LocationForecastDTO;
import com.example.WindsurferWeather.Dto.WeatherData;
import com.example.WindsurferWeather.WeatherService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationForecastDTOTest {

    @Test
    void calculateDreamFactor() {

        //given
        WeatherData weatherData = new WeatherData();
        weatherData.setCity_name("Dakhla");
        weatherData.setCountry_code("MA");
        LocationForecast locationForecast = new LocationForecast();
        locationForecast.setTemp(22.1);
        locationForecast.setWind_spd(5.1845);
        LocationForecast[] data = new LocationForecast[2];
        data[1] = locationForecast;
        weatherData.setData(data);

        //when
        LocationForecastDTO locationForecastDTO = new LocationForecastDTO(weatherData, 1);

        //then
        assertEquals("Dakhla", locationForecastDTO.getCityName());
        assertEquals("MA", locationForecastDTO.getCountryCode());
        assertEquals(22.1, locationForecastDTO.getTemp());
        assertEquals(5.1845, locationForecastDTO.getWind_spd());
        assertEquals(5.1845 * 3 + 22.1, locationForecastDTO.calculateDreamFactor());
    }
}