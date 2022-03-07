package com.jcdecaux.kata.service;

import com.jcdecaux.kata.model.Poi;
import com.jcdecaux.kata.model.Zone;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DensityService {

    Long getDensity(Double minLat, Double minLong, List<Poi> pois);

    List<Zone> getMostDenseZones(Integer n, List<Poi> pois);


}
