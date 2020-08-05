package com.example.WindsurferWeather;

import com.example.WindsurferWeather.Dto.LocationEntity;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface LocationRepository extends Repository<LocationEntity, Long> {

    List<LocationEntity> findAll();

}