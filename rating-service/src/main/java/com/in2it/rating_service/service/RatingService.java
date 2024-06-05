package com.in2it.rating_service.service;

import java.util.List;

import com.in2it.rating_service.entity.Rating;

public interface RatingService {
	
	Rating saveRating(Rating rating);
	List<Rating> getAllRatings();
	Rating getRatingById(int id);
	Rating updateRating(Rating rating);
	List<Rating> getRatingsByUserId(int id);
	List<Rating> getRatingsByHotelId(int id);
	boolean deleteRating(int id);
	

}
