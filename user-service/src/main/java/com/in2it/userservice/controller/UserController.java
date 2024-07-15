package com.in2it.userservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in2it.userservice.entities.Rating;
import com.in2it.userservice.entities.Users;
import com.in2it.userservice.responce.ResponceStructure;
import com.in2it.userservice.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService service;
	
	@Autowired
	ResponceStructure<Users> responceStructure;
	
	@PostMapping()
	public ResponceStructure<Users> saveUserController(@RequestBody Users users) {
		
		System.out.println("data from user controller rating============"+users.getRatings());
		List<Rating> ratings = users.getRatings();
		for (Rating rating : ratings) {
			System.out.println("hotal data from user"+rating.getHotel());
		}
		
		 Users savedUser = service.saveUser(users);
		 responceStructure.setData(savedUser);
		 responceStructure.setMessage("A new User created "+savedUser.toString());
		 responceStructure.setStatus(HttpStatus.ACCEPTED.name());
		 responceStructure.setStatusCode(HttpStatus.ACCEPTED.value());
		 
		 return responceStructure;
	}
	
	
	@GetMapping
	@CircuitBreaker(name = "ratingHotelBroker", fallbackMethod = "ratingHotelGetAllFallBack")
	public List<Users> getAllUser() {
		return service.getAllUser();
	}
	
	@GetMapping("/user/{id}")
	public Users getUserById(@PathVariable int id) {
		return service.getUserById(id);
	}

	@PutMapping("/user/{id}")
	public Users updateUser(@RequestBody Users users) {
		return service.updateUser(users);
	}
	
	
	@DeleteMapping("user/{id}")
	public boolean deleteUserById(@PathVariable int id) {
		return service.deleteUserById(id);
	}
	
	public List<Users> ratingHotelGetAllFallBack(Exception exception){
		
		System.out.println("Some service is down for more detaails follow this exception \n\n\n\n\n"+exception);
		return new ArrayList<>();
	}


}
