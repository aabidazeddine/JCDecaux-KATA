package com.jcdecaux.kata.service.impl;

import com.jcdecaux.kata.model.Poi;
import com.jcdecaux.kata.model.Zone;
import com.jcdecaux.kata.service.DensityService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.Double.valueOf;
import static java.lang.Math.abs;

@Service
public class DensityServiceImpl implements DensityService {

    public List<Zone> getMostDenseZones(Integer n, List<Poi> pois) {
        return buildZones(pois).entrySet().stream()
                .sorted(Collections.reverseOrder(Comparator.comparingInt(e -> e.getValue().size())))
                .map(Map.Entry::getKey)
                .limit(n)
                .collect(Collectors.toList());
    }

    public Long getDensity(Double minLat, Double minLong, List<Poi> pois) {
        return pois.stream()
                .filter(poi -> poi.getLatitude() > minLat && poi.getLongitute() > minLong)
                .count();
    }

    private Map<Zone, List<Poi>> buildZones(List<Poi> pois) {
        return pois.stream()
                .collect(Collectors.groupingBy(this::identifyZone));
    }

    private Zone identifyZone(Poi poi) {
        Double bornLatMin = calculateBornInf(poi.getLatitude());
        Double bornLonMin = calculateBornInf(poi.getLongitute());

        return Zone.builder()
                .minLat(bornLatMin)
                .maxLat(bornLatMin + 0.5)
                .minLong(bornLonMin)
                .maxLong(bornLonMin + 0.5)
                .build();
    }

    private Double calculateBornInf(Double d) {
        BigDecimal bigDecimal = BigDecimal.valueOf(d);
        int intValue = bigDecimal.intValue();
        double decimalPart = abs(Double.parseDouble(bigDecimal.subtract(new BigDecimal(intValue)).toPlainString()));

        if (d < 0) {
            if (decimalPart > 0.5) {
                return (double) (intValue - 1);
            } else {
                return intValue - 0.5;
            }
        } else {
            if (decimalPart < 0.5) {
                return (double) intValue;
            } else {
                return intValue + 0.5;
            }
        }
    }

}
