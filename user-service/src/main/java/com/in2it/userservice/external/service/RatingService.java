package com.in2it.userservice.external.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.in2it.userservice.entities.Rating;


@FeignClient(name = "RATING-SERVICE")
public interface RatingService {
	
	@PostMapping("/ratings")
	public Rating saveRating(@RequestBody Rating rating);
	
	@GetMapping("/ratings")
	public List<Rating> getAllRatings();
	
	
	@GetMapping("rating/{id}")
	public Rating getRatingByIdController(@PathVariable("id") int id);
	
	
	@PutMapping("/ratings")
	public Rating updateRating(@RequestBody Rating rating);
	
	@DeleteMapping("/ratings/rating/{id}")
	public boolean deleteRating(@PathVariable int id);
	
	@GetMapping("/ratings/rating/user/{id}")
	public List<Rating> getRatingsByUserId(@PathVariable int id);
	
	@GetMapping("/ratings/rating/hotel/{id}")
	public List<Rating> getRatingsByHotelId(@PathVariable int id);
	
	@PatchMapping("/ratings/feedback")
	public Rating updateRatingFeedback(@RequestBody Rating rating);

}
