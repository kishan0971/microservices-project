package com.in2it.rating_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RatingDto {
	
	private int ratingId;
	private int hotelId;
	private int userId;
	private int rating;
	private String feedback;

}
