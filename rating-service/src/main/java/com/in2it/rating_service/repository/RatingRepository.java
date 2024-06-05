package com.in2it.rating_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in2it.rating_service.entity.Rating;


@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
	
	List<Rating> findByUserId(int userId);
	List<Rating> findByHotelId(int hotelId);

}
