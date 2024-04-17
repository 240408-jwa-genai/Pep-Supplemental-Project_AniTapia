package com.revature.service;

import com.revature.models.User;
import com.revature.models.UsernamePasswordAuthentication;
import com.revature.repository.UserDao;

public class UserService {

	private UserDao dao;

	public UserService(UserDao dao){
		this.dao = dao;
	}

	public User authenticate(UsernamePasswordAuthentication loginRequestData) {
		User possibleUser = dao.getUserByUsername(loginRequestData.getUsername());
		if(possibleUser != null){
			boolean passwordMatch = loginRequestData.getPassword().equals(possibleUser.getPassword());
			if(passwordMatch){
				return possibleUser;
			}
		}
		return new User();
	}

	public User register(User registerRequestData) {
		// TODO: implement
		if(registerRequestData.getUsername().length() <= 30 && registerRequestData.getPassword().length() <= 30){
			User databaseData = dao.getUserByUsername(registerRequestData.getUsername());
			if(databaseData != null){
				String usernameFromDatabase = databaseData.getUsername();
				String usernameFromRegisterRequest = registerRequestData.getUsername();
				if(!usernameFromRegisterRequest.equals(usernameFromDatabase)){
					UsernamePasswordAuthentication upa = new UsernamePasswordAuthentication();
					upa.setUsername(usernameFromRegisterRequest);
					upa.setPassword(registerRequestData.getPassword());
					return dao.createUser(upa);
				}
			}
		}
		return new User();
	}
}
