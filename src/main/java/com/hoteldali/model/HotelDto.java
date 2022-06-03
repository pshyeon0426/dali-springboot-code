package com.hoteldali.model;

import com.hoteldali.model.domain.Hotel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HotelDto {
    private String namekor;
    private String nameeng;
    private String location;
    private int star;
    private String details;
    private String category;
    private Long viewcount;
    private Long recentcount;
    private double lat;
    private double lng;

    //public Hotel toEntity() {
    //    return new Hotel(namekor, nameeng, location, star, details, category, viewcount, recentcount, lat, lng);
    //}

    public HotelDto(Hotel hotel) {
        this.namekor = hotel.getNamekor();
        this.nameeng = hotel.getNameeng();
        this.category = hotel.getCategory();
        this.details = hotel.getDetails();
        this.star = hotel.getStar();
        this.location = hotel.getLocation();
        this.viewcount = hotel.getViewcount();
        this.recentcount = hotel.getRecentcount();
        this.lat = hotel.getLat();
        this.lng = hotel.getLng();
    }
}
