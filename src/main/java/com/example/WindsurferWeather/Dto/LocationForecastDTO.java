package com.example.WindsurferWeather.Dto;

public class LocationForecastDTO {

    private String cityName;
    private String countryCode;
    private double wind_spd;
    private double temp;

    public LocationForecastDTO(WeatherData weatherData, int dayIndex) {
        LocationForecast forecast = weatherData.getData()[dayIndex];
        cityName = weatherData.getCity_name();
        countryCode = weatherData.getCountry_code();
        wind_spd = forecast.getWind_spd();
        temp = forecast.getTemp();
    }

    public double calculateDreamFactor() {
        return wind_spd * 3 + temp;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public double getWind_spd() {
        return wind_spd;
    }

    public double getTemp() {
        return temp;
    }

}
