package com.in2it.rating_service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.in2it.rating_service.entity.Rating;
import com.in2it.rating_service.exception.RatingNotFoundException;
import com.in2it.rating_service.repository.RatingRepository;
import com.in2it.rating_service.service.RatingService;


@Service
public class RatingServiceImpl implements RatingService {
	
	@Autowired
	RatingRepository repository;
	
	@Autowired
	private RestTemplate template;

	@Override
	public Rating saveRating(Rating rating) {
		
		return repository.save(rating);
	}

	@Override
	public List<Rating> getAllRatings() {
		
		return repository.findAll();
	}

	@Override
	public Rating getRatingById(int id) {
		
		return repository.findById(id).orElseThrow(()-> new RatingNotFoundException("Rating dosen't exist with given id"));
	}

	@Override
	public Rating updateRating(Rating rating) {
		Rating exstRating = repository.findById(rating.getRatingId()).orElseThrow(()-> new RatingNotFoundException("Rating dosen't exist with given id"));
		if(exstRating != null) {
			if(rating.getFeedback()!=null) {
				
				exstRating.setFeedback(rating.getFeedback());
			}
			exstRating.setHotelId(rating.getHotelId());
			exstRating.setRating(rating.getRating());
			exstRating.setRatingId(rating.getRatingId());
			exstRating.setUserId(rating.getUserId());
			
			return repository.save(exstRating);
		}
		return null;
	}

	@Override
	public boolean deleteRating(int id) {
		Rating exstRating = repository.findById(id).orElseThrow(()-> new RatingNotFoundException("Rating dosen't exist with given id"));
		if(exstRating != null) {
			repository.delete(exstRating);
			return true;
		}
		return false;
	}

	@Override
	public List<Rating> getRatingsByUserId(int id) {
		
		return repository.findByUserId(id);
	}

	@Override
	public List<Rating> getRatingsByHotelId(int id) {
		
		return repository.findByHotelId(id);
	}

	@Override
	public Rating updateRatingFeedback(Rating rating) {
		Rating exstRating = repository.findById(rating.getRatingId()).orElseThrow(()-> new RatingNotFoundException("Rating dosen't exist with given id"));
		if(exstRating != null) {
			if(rating.getFeedback()!=null) {
				
				exstRating.setFeedback(rating.getFeedback());
			}
			
			
			return repository.save(exstRating);
		}
		return null;
	}

	

}
