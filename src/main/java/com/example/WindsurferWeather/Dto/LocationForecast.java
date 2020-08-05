package com.example.WindsurferWeather.Dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationForecast {

    private double wind_spd;
    private double temp;

    public double getWind_spd() {
        return wind_spd;
    }

    public void setWind_spd(double wind_spd) {
        this.wind_spd = wind_spd;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

}
