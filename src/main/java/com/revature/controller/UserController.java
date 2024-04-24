package com.revature.controller;

import com.revature.MainDriver;
import com.revature.models.User;
import com.revature.models.UsernamePasswordAuthentication;
import com.revature.service.UserService;


public class UserController {
	
	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	public Boolean authenticate(UsernamePasswordAuthentication loginRequestData) {
		User possibleUser = userService.authenticate(loginRequestData);
		if(possibleUser.getId() != 0){
			MainDriver.loggedInUserId = possibleUser.getId();
			return true;
		}
		else return false;
	}

	public void register(User registerRequestData) {
		// TODO: implement
		User userResponse = userService.register(registerRequestData);
		if(userResponse.getId() != 0)System.out.println("Registration successfully!");
		else System.out.println("Registration failed: please double check your username and password and try again.");
	}

}
