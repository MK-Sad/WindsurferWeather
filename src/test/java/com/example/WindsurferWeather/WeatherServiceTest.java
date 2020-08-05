package com.example.WindsurferWeather;

import com.example.WindsurferWeather.Dto.LocationEntity;
import com.example.WindsurferWeather.Dto.LocationForecast;
import com.example.WindsurferWeather.Dto.LocationForecastDTO;
import com.example.WindsurferWeather.Dto.WeatherData;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WeatherServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private WeatherService weatherService = new WeatherService();

    @Test
    public void getBestLocationTest() {
        //given
        ReflectionTestUtils.setField(weatherService, "weatherbitUrl", "https://api.weatherbit.io/v2.0/forecast/daily");
        ReflectionTestUtils.setField(weatherService, "weatherbitKey", "someKey");

        List<LocationEntity> locationsList = new ArrayList<>();
        LocationEntity location1 = new LocationEntity();
        location1.setCityName("Dakhla");
        location1.setCountryCode("MA");
        locationsList.add(location1);
        LocationEntity location2 = new LocationEntity();
        location1.setCityName("Poznan");
        location1.setCountryCode("PL");
        locationsList.add(location2);

        String url1 = "https://api.weatherbit.io/v2.0/forecast/daily?city=Dakhla&country=MA&key=someKey";
        String url2 = "https://api.weatherbit.io/v2.0/forecast/daily?city=Poznan&country=PL&key=someKey";

        WeatherData weatherData1 = new WeatherData();
        weatherData1.setCity_name("Dakhla");
        weatherData1.setCountry_code("MA");
        LocationForecast locationForecast1 = new LocationForecast();
        locationForecast1.setTemp(22.1);
        locationForecast1.setWind_spd(5.1845);
        LocationForecast[] data1 = new LocationForecast[2];
        data1[1] = locationForecast1;
        weatherData1.setData(data1);

        WeatherData weatherData2 = new WeatherData();
        weatherData2.setCity_name("Poznan");
        weatherData2.setCountry_code("PL");
        LocationForecast locationForecast2 = new LocationForecast();
        locationForecast2.setTemp(23.1);
        locationForecast2.setWind_spd(10);
        LocationForecast[] data2 = new LocationForecast[2];
        data2[1] = locationForecast2;
        weatherData2.setData(data2);

        Mockito.when(restTemplate.getForObject(url1, WeatherData.class))
                .thenReturn(weatherData1);
        Mockito.when(restTemplate.getForObject(url2, WeatherData.class))
                .thenReturn(weatherData2);

        Mockito.when(locationRepository.findAll())
                .thenReturn(locationsList);

        LocalDate testLocalDate = LocalDate.now().plusDays(1);
        Date testDate = Date.from(testLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        //when
        LocationForecastDTO resultLocationDTO = weatherService.getBestLocation(testDate);

        //then
        assertEquals("Poznan", resultLocationDTO.getCityName());
    }

}
