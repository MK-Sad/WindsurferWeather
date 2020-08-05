package com.example.WindsurferWeather;

import com.example.WindsurferWeather.Dto.LocationEntity;
import com.example.WindsurferWeather.Dto.LocationForecastDTO;
import com.example.WindsurferWeather.Dto.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class WeatherService {

    @Value("${weatherbit.url}")
    private String weatherbitUrl;

    @Value("${weatherbit.key}")
    private String weatherbitKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LocationRepository locationRepository;

    public LocationForecastDTO getBestLocation(Date date) {
        List<LocationForecastDTO> result = getForecastForAllLocations(calculateDayIndex(date));
        return result.stream()
                .filter(location -> location.getWind_spd() > 5 && location.getWind_spd() < 18)
                .filter(location -> location.getTemp() > 5 && location.getTemp() < 35)
                .max(Comparator.comparing(LocationForecastDTO::calculateDreamFactor))
                .orElse(null);
    }

    private int calculateDayIndex(Date date) {
        Date currentDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        long daysDifference = ChronoUnit.DAYS.between(currentDate.toInstant(), date.toInstant());
        if (daysDifference >= 0 && daysDifference < 16) {
            return (int) daysDifference;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong date", null);
        }
    }

    private List<LocationForecastDTO> getForecastForAllLocations(int dayIndex) {
        List<LocationForecastDTO> result = new ArrayList<>();
        List<LocationEntity> myLocations = locationRepository.findAll();
        for (LocationEntity location : myLocations) {
            WeatherData weatherData = getForecast(location);
            if(weatherData != null){
                LocationForecastDTO locationForecastDTO = new LocationForecastDTO(weatherData, dayIndex);
                result.add(locationForecastDTO);
            }
        }
        return result;
    }

    private WeatherData getForecast(LocationEntity location) {
        String url = weatherbitUrl + "?city=" + location.getCityName()
                + "&country=" + location.getCountryCode() + "&key=" + weatherbitKey;
        WeatherData weatherData = restTemplate.getForObject(url, WeatherData.class);
        return weatherData;
    }
}
