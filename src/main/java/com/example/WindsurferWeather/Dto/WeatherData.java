package com.example.WindsurferWeather.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherData {

    private String city_name;
    private String country_code;
    private double lon;
    private double lat;

    private LocationForecast[] data;

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setData(LocationForecast[] data) {
        this.data = data;
    }

    LocationForecast[] getData() {
        return data;
    }

}
