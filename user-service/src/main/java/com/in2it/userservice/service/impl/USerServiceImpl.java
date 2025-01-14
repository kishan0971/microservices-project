package com.in2it.userservice.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.in2it.userservice.entities.Hotel;
import com.in2it.userservice.entities.Rating;
import com.in2it.userservice.entities.Users;
import com.in2it.userservice.exception.InvalidDataException;
import com.in2it.userservice.exception.UserNotFoundException;
import com.in2it.userservice.external.service.HotelService;
import com.in2it.userservice.external.service.RatingService;
import com.in2it.userservice.repository.UserRepository;
import com.in2it.userservice.service.UserService;

@Service
public class USerServiceImpl implements UserService{
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private RestTemplate template;
	
	@Autowired
	private RatingService ratingService;
	
	
	@Autowired
	private HotelService hotelService;
	
	private static final Logger LOGGER = LogManager.getLogger(USerServiceImpl.class);

	@Override
	public Users saveUser(Users users) {
		System.out.println("user from useer service ==================================="+users);
		List<Rating> ratings = users.getRatings();
		if(!ratings.isEmpty()) {
			
			Users savedUser = repository.save(users);
			for (Rating rating : ratings) {
				
				Hotel hotel = rating.getHotel(); 
				if(hotel != null) {
//					Hotel savedHotel= template.postForObject("http://HOTEL-SERVICE/hotels", hotel, Hotel.class);
					Hotel savedHotel = hotelService.saveHotel(hotel);
					rating.setHotel(savedHotel);
					rating.setHotelId(savedHotel.getId());
					
				}
				rating.setUserId(savedUser.getId());
//				Rating savedRating = template.postForObject("http://RATING-SERVICE/ratings", rating, Rating.class);
				Rating savedRating = ratingService.saveRating(rating);
				rating.setRatingId(savedRating.getRatingId());
			}
		}

		
		return repository.save(users);
	}

	@Override
	public List<Users> getAllUser() {
		
		List<Users> users = repository.findAll();
		
		for (Users user : users) {
			List<Rating> ratingList = ratingService.getAllRatings();
//			Rating[] ratings = template.getForObject("http://RATING-SERVICE/ratings/rating/user/"+user.getId(), Rating[].class);
//			List<Rating> ratingList = Arrays.asList(ratings);
			
			for (Rating rating : ratingList) {
				System.out.println("ratings "+ rating);
//				ResponseEntity<Hotel> hotelEntiy = template.getForEntity("http://HOTEL-SERVICE/hotels/hotel/"+rating.getHotelId(), Hotel.class);
//				LOGGER.info("hotelEntiy ======================"+hotelEntiy);
//				Hotel hotel = hotelEntiy.getBody();
//				LOGGER.info("hotel ======================"+hotel);
//				rating.setHotel(hotel);
//				===============fignclient====================================
				Hotel hotel = hotelService.getHotelById(rating.getHotelId());
				rating.setHotel(hotel);
				
			}
			

//			user.setRatings(ratings);
			user.setRatings(ratingList);
		}
		
		return users;
	}

	@Override
	public Users getUserById(int id) {

		Users user = repository.findById(id).orElseThrow(()->new UserNotFoundException("User dosen't exist with given id"));
//		Rating[] ratings = template.getForObject("http://localhost:8083/ratings/rating/user/"+user.getId(), Rating[].class);
		
////		using rest template
//		Rating[] ratings = template.getForObject("http://RATING-SERVICE/ratings/rating/user/"+user.getId(), Rating[].class);
//		List<Rating> ratingList = Arrays.asList(ratings);
		
//		using feignclient
		List<Rating> ratingList = ratingService.getRatingsByUserId(user.getId());
		
		
		
//		LOGGER.info("Ratings of a user"+ratings);
		for (Rating rating : ratingList) {
			System.out.println("ratings "+ rating);
//			ResponseEntity<Hotel> hotelEntiy = template.getForEntity("http://localhost:8084/hotels/hotel/"+rating.getHotelId(), Hotel.class);
			ResponseEntity<Hotel> hotelEntiy = template.getForEntity("http://HOTEL-SERVICE/hotels/hotel/"+rating.getHotelId(), Hotel.class);
			LOGGER.info("hotelEntiy ======================"+hotelEntiy);
			Hotel hotel = hotelEntiy.getBody();
			LOGGER.info("hotel ======================"+hotel);
			rating.setHotel(hotel);
			
		}
		user.setRatings(ratingList);
		
		return user;
	}

	@Override
	public Users updateUser(Users users) {
		Users extUser = repository.findById(users.getId()).orElseThrow(()->new UserNotFoundException("User dosen't exist with given id"));
		LOGGER.info("Old Data to get Updated "+extUser.toString());
		if(extUser!=null) {
			extUser.setId(users.getId());
			extUser.setAbout(users.getAbout());
			extUser.setEmail(users.getEmail());
			extUser.setName(users.getName());
			
			List<Rating> ratings = users.getRatings();
			if(!ratings.isEmpty()) {
				
//				Users savedUser = repository.save(users);
				for (Rating rating : ratings) {
					Rating exstRating = template.getForObject("http://RATING-SERVICE/ratings/rating/"+rating.getRatingId(), Rating.class);
					if(exstRating.getUserId()==extUser.getId()) {
						
						LOGGER.info("exsisting ratings =========="+exstRating);
						Hotel hotel = rating.getHotel(); 
						if(hotel != null) {
							Hotel savedHotel= template.postForObject("http://HOTEL-SERVICE/hotels", hotel, Hotel.class);
							rating.setHotel(savedHotel);
							rating.setHotelId(savedHotel.getId());
							
						}
//					rating.setUserId(savedUser.getId());
//						template.put("http://RATING-SERVICE/ratings", rating);
//						template.patchForObject("http://RATING-SERVICE/ratings/feedback", rating, Rating.class);
						ratingService.updateRatingFeedback(rating);
						rating.setUserId(extUser.getId());
//						rating.setRatingId(savedRating.getRatingId());
					}
					
				}
			}
			extUser.setRatings(ratings);
			Users savedUser = repository.save(extUser);
			LOGGER.info("New data after update "+savedUser.toString());
			return savedUser;
		}
		return null;
	}

	@Override
	public boolean deleteUserById(int id) {
		Users extUser = repository.findById(id).orElseThrow(()->new UserNotFoundException("User dosen't exist with given id"));
		if(extUser!=null) {

			Rating[] ratings = template.getForObject("http://RATING-SERVICE/ratings/rating/user/"+extUser.getId(), Rating[].class);
			List<Rating> ratingList = Arrays.asList(ratings);
			
			for (Rating rating : ratingList) {
				template.delete("http://RATING-SERVICE/ratings/rating/"+rating.getRatingId());
			}
			
			repository.delete(extUser);
			return true;
		}
		return false;
	}
	
	
	
//	==============================validator functions===================================================
	
	 // Function to validate the username
	public boolean isValidUsername(String name)
   {
       String regex = "^[a-zA-Z]+(?:\\s+[a-zA-Z]+)*$";
       Pattern p = Pattern.compile(regex);
       if (name == null) {
           throw new InvalidDataException("Name should contain only alphabetical charector and not more than one space");
       }
       Matcher m = p.matcher(name);
       return m.matches();
   }
   
   
   

}




