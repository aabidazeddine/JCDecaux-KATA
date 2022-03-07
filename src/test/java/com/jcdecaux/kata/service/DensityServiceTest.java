package com.jcdecaux.kata.service;

import com.jcdecaux.kata.model.Poi;
import com.jcdecaux.kata.model.Zone;
import com.jcdecaux.kata.service.impl.DensityServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class DensityServiceTest {

    @InjectMocks
    private DensityServiceImpl densityServiceImpl;

    private List<Poi> pois;

    @BeforeEach
    public void setUp() {
        pois = new ArrayList<>();
        pois.add(Poi.builder().id("id1").latitude(-48.6).longitute(-37.7).build());
        pois.add(Poi.builder().id("id2").latitude(-27.1).longitute(8.4).build());
        pois.add(Poi.builder().id("id3").latitude(6.6).longitute(-6.9).build());
        pois.add(Poi.builder().id("id4").latitude(-2.3).longitute(38.3).build());
        pois.add(Poi.builder().id("id5").latitude(6.8).longitute(-6.9).build());
        pois.add(Poi.builder().id("id6").latitude(-2.5).longitute(38.3).build());
        pois.add(Poi.builder().id("id7").latitude(0.1).longitute(-0.1).build());
        pois.add(Poi.builder().id("id8").latitude(-2.1).longitute(38.1).build());
    }

    @Test
    public void it_should_return_most_dense_zones() {
        // When
        List<Zone> zones = densityServiceImpl.getMostDenseZones(2, pois);

        // Then
        assertThat(zones).hasSize(2);
        assertThat(zones.get(0)).isEqualTo(Zone.builder()
                .minLat(-2.5)
                .maxLat(-2.0)
                .minLong(38.0)
                .maxLong(38.5)
                .build());
        assertThat(zones.get(1)).isEqualTo(Zone.builder()
                .minLat(6.5)
                .maxLat(7.0)
                .minLong(-7.0)
                .maxLong(-6.5)
                .build());

    }

    @Test
    public void it_should_count_pois_in_given_zone() {
        // Given
        Double minLat = 6.5;
        Double minLong = -7.0;

        // When
        Long countZones = densityServiceImpl.getDensity(minLat, minLong, pois);

        // Then
        assertThat(countZones).isEqualTo(2);
    }

}
