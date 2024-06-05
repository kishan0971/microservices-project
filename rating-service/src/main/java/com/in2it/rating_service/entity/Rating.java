package com.in2it.rating_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Rating {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RATINGID")
	private int ratingId;
	
	@Column(name = "HOTELID")
	private int hotelId;
	
	@Column(name = "USERID")
	private int userId;
	
	@Column(name = "RATING")
	private int rating;
	
	@Column(name = "FEEDBACK")
	private String feedback;
	
	@Transient
	private Hotel hotel;
}
