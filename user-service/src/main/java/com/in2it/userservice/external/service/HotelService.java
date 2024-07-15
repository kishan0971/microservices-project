package com.in2it.userservice.external.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.in2it.userservice.entities.Hotel;



@FeignClient(name ="HOTEL-SERVICE")
public interface HotelService {
	
	
	@PostMapping("/hotels")
	public Hotel saveHotel(@RequestBody Hotel hotel) ;
	
	
	@GetMapping("/hotels")
	public List<Hotel> getAllHotels();
	
	@GetMapping("/hotels/hotel/{id}")
	public Hotel getHotelById(@PathVariable int id);
	
	@PutMapping("/hotels")
	public Hotel updateHotel(@RequestBody Hotel hotel);
	
	
	@DeleteMapping("/hotels/hotel/{id}")
	public boolean deleteHotel(@PathVariable int id);


}
