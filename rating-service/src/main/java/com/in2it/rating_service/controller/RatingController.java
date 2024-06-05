package com.in2it.rating_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in2it.rating_service.entity.Rating;
import com.in2it.rating_service.exception.RatingNotFoundException;
import com.in2it.rating_service.service.RatingService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/ratings")
public class RatingController {
	
	@Autowired
	RatingService service;
	
	
	@PostMapping
	public Rating saveRatingController(@RequestBody Rating rating) {
		
		return service.saveRating(rating);
	}
	
	
	@GetMapping
	public List<Rating> getAllRatingsController() {
		return service.getAllRatings();
	}

	@GetMapping("rating/{id}")
	public Rating getRatingByIdController(@PathVariable int id) {
		return service.getRatingById(id);
	}
	
	@PutMapping
	public Rating updateRating(@RequestBody Rating rating) {
		return service.updateRating(rating);	
	}

	@DeleteMapping("rating/{id}")
	public boolean deleteRating(@PathVariable int id) {
		return service.deleteRating(id);
	}
	
	@GetMapping("rating/user/{id}")
	public List<Rating> getRatingsByUserId(@PathVariable int id) {
		return service.getRatingsByUserId(id);
	}
	
	@GetMapping("rating/hotel/{id}")
	public List<Rating> getRatingsByHotelId(@PathVariable int id) {
		return service.getRatingsByHotelId(id);
	}


}
