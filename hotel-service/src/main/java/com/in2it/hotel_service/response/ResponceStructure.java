package com.in2it.hotel_service.response;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class ResponceStructure<T> {
	
	private String status;
	private String message;
	private int statusCode;
	private T data;
}
