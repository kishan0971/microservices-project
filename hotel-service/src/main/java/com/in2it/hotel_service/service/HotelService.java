package com.in2it.hotel_service.service;

import java.util.List;

import com.in2it.hotel_service.entity.Hotel;

public interface HotelService {
	
	Hotel saveHotel(Hotel hotel);
	List<Hotel> getAllHotels();
	Hotel getHotelById(int id);
	Hotel updateHotel(Hotel hotel);
	boolean deleteHotel(int id);

}
