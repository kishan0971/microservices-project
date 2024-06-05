package com.in2it.user_service.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
	
	private int ratingId;
	private int hotelId;
	private int userId;
	private int rating;
	private String feedback;
	private Hotel hotel;

}
