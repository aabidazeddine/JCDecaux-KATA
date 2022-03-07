package com.jcdecaux.kata.model;


import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode
public class Zone {
    Double minLat;
    Double maxLat;
    Double minLong;
    Double maxLong;
}
