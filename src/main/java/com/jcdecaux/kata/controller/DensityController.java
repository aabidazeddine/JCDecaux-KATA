package com.jcdecaux.kata.controller;

import com.jcdecaux.kata.model.Poi;
import com.jcdecaux.kata.model.Zone;
import com.jcdecaux.kata.service.Utils;
import com.jcdecaux.kata.service.impl.DensityServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController("/")
public class DensityController {

    private final DensityServiceImpl densityService;

    public DensityController(DensityServiceImpl densityService) {
        this.densityService = densityService;
    }

    @PostMapping("/density")
    public Long getDensity(@RequestParam("minLat") Double minLat,
                           @RequestParam("minLong") Double minLong) throws IOException {
        List<Poi> pois = Utils.readFile();
        return densityService.getDensity(minLat, minLong, pois);
    }

    @PostMapping("/denseZones")
    public List<Zone> getMostDenseZones(@RequestParam("zoneCount") Integer zoneCount) throws IOException {
        List<Poi> pois = Utils.readFile();
        return densityService.getMostDenseZones(zoneCount, pois);
    }
}
