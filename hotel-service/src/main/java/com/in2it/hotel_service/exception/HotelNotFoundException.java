package com.in2it.hotel_service.exception;

public class HotelNotFoundException extends RuntimeException {
	
	public HotelNotFoundException(String msg) {
		super(msg);
	}

}
