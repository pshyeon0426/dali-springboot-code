package com.hoteldali.model;

import com.hoteldali.model.domain.Hotel;

import lombok.Getter;

@Getter
public class LocationDto {
    private double longitude;
    private double latitude;
    private String position;

    public LocationDto(Hotel hotel) {
        this.position = hotel.getLocation();
        this.latitude = hotel.getLat();
        this.longitude = hotel.getLng();
    }
}
