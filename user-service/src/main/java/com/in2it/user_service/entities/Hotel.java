package com.in2it.user_service.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Hotel {
	
	private int id;
	private String name;
	private String about;
	private String location;

}