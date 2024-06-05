package com.in2it.hotel_service.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExceptionResponse {
	
	private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

}
