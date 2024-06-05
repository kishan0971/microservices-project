package com.in2it.hotel_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in2it.hotel_service.entity.Hotel;
import com.in2it.hotel_service.service.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {
	
	@Autowired
	HotelService service;
	
	@PostMapping
	public Hotel saveHotel(@RequestBody Hotel hotel) {
		return service.saveHotel(hotel);
	}
	
	
	@GetMapping
	public List<Hotel> getAllHotels() {
		return service.getAllHotels();
	}
	
	@GetMapping("hotel/{id}")
	public Hotel getHotelById(@PathVariable int id) {
		return service.getHotelById(id);	
	}
	
	@PutMapping
	public Hotel updateHotel(@RequestBody Hotel hotel) {
		return service.updateHotel(hotel);
	}
	
	@DeleteMapping("hotel/{id}")
	public boolean deleteHotel(@PathVariable int id) {
		return service.deleteHotel(id);
	}
	
	
	

}
