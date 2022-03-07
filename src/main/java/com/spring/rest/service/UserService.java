package com.spring.rest.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.spring.rest.exceptions.UserNotFoundException;
import com.spring.rest.model.User;

@Component
public interface UserService {
	public String createUser(User user);
	public String updateUser(int id,User user) throws UserNotFoundException;
	public String deleteUser(int id) throws UserNotFoundException;
	public String deleteAllUsers();
	public User findUser(int id) throws UserNotFoundException;
	public List<User> findAllUsers();
 
}
