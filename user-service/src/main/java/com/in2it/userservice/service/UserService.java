package com.in2it.userservice.service;

import java.util.List;

import com.in2it.userservice.entities.Users;

public interface UserService {
	
	Users saveUser(Users users);
	List<Users> getAllUser();
	Users getUserById(int id);
	Users updateUser(Users users);
	boolean deleteUserById(int id);

}
