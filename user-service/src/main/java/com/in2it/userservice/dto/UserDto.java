package com.in2it.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
//@Data
public class UserDto {
	
	private int id;
	private String name;
	private String email;
	private String about;
	

}
