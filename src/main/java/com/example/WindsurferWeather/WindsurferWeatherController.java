package com.example.WindsurferWeather;

import com.example.WindsurferWeather.Dto.LocationForecastDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
public class WindsurferWeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/bestLocationForDay")
    LocationForecastDTO getBestLocationForGivenDay(@RequestParam(value = "date")
                                                   @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return weatherService.getBestLocation(date);
    }
}
