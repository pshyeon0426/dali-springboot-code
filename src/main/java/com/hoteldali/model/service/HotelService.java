package com.hoteldali.model.service;

import java.util.ArrayList;
import java.util.List;

import com.hoteldali.data.HotelRepository;
import com.hoteldali.model.HotelDetailDto;
import com.hoteldali.model.HotelDto;
import com.hoteldali.model.HotelTopDto;
import com.hoteldali.model.domain.Hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelService {
    
    private final HotelRepository hotelRepo;
	
	@Autowired
	public HotelService(HotelRepository hotelRepo) {
		this.hotelRepo = hotelRepo;
	}

    public List<HotelTopDto> listHotelTop10Details() {
        List<Hotel> hotels = hotelRepo.findTop10ByOrderByViewcountDesc();
        List<HotelTopDto> top10 = new ArrayList<HotelTopDto>();
        int rank = 1;
        for (Hotel hotel : hotels) {
            top10.add(new HotelTopDto(hotel, rank));
            rank++;
        }
        return top10;
    }

    public List<String> listHotelTop10() {
        List<Hotel> hotels = hotelRepo.findTop10ByOrderByViewcountDesc();
        List<String> top10 = new ArrayList<String>();
        for (Hotel hotel : hotels) {
            top10.add(hotel.getNamekor());
        }
        return top10;
    }

    public List<String> listHotelByNamekorContains(String name) {
        List<String> hotels = new ArrayList<String>();
        try {
            List<Hotel> thotels = hotelRepo.findByNamekorContains(name);
            for (Hotel hotel : thotels) {
                hotels.add(hotel.getNamekor());
            }
            return hotels;
        }
        catch(Exception e) {
            hotels.add("");
            return hotels;
        }
    }

    public List<HotelDto> listHotelByNamekorDetails(String name) {
        List<HotelDto> hotels = new ArrayList<HotelDto>();
        List<Hotel> thotels = hotelRepo.findByNamekor(name);
        for (Hotel hotel : thotels) {
            hotels.add(new HotelDto(hotel));
        }
        return hotels;
    }

    public HotelDetailDto getHotelById(Long id) {
        Hotel thotel = hotelRepo.findById(id);
        if (thotel != null) {
            HotelDetailDto hotel = new HotelDetailDto(thotel);
            return hotel;
        }
        else return null;
    }
}
