package com.in2it.hotel_service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in2it.hotel_service.entity.Hotel;
import com.in2it.hotel_service.exception.HotelNotFoundException;
import com.in2it.hotel_service.repository.HotelRepository;
import com.in2it.hotel_service.service.HotelService;


@Service
public class HotelServiceImpl implements HotelService {
	
	@Autowired
	HotelRepository repository;
	
	@Override
	public Hotel saveHotel(Hotel hotel) {
		
		return repository.save(hotel);
	}

	@Override
	public List<Hotel> getAllHotels() {
		
		return repository.findAll();
	}

	@Override
	public Hotel getHotelById(int id) {
		
		return repository.findById(id).orElseThrow(()-> new HotelNotFoundException("Hotel dosen't exist with given id. "));
	}

	@Override
	public Hotel updateHotel(Hotel hotel) {
		
		Hotel exstHotel = repository.findById(hotel.getId()).orElseThrow(()-> new HotelNotFoundException("Hotel dosen't exist with given id. "));
		if(exstHotel!=null) {
			exstHotel.setId(hotel.getId());
			exstHotel.setAbout(hotel.getAbout());
			exstHotel.setLocation(hotel.getLocation());
			exstHotel.setName(hotel.getName());
			
			return repository.save(exstHotel);
		}
		
		return null;
	}

	@Override
	public boolean deleteHotel(int id) {
		Hotel exstHotel = repository.findById(id).orElseThrow(()-> new HotelNotFoundException("Hotel dosen't exist with given id. "));
		if(exstHotel!=null) {
			repository.delete(exstHotel);
			return true;
		}
		
		return false;
	}

}
