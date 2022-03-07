package com.spring.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.rest.exceptions.UserNotFoundException;
import com.spring.rest.model.User;
import com.spring.rest.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public String createUser(User user) {
		userRepository.save(user);
		return "User created Successfully";
	}


	@Override
	public String deleteUser(int id) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent()) {
			userRepository.delete(user.get());
			return "User deleted successfully";
		}else {
			throw new UserNotFoundException(id);
		}
	}

	@Override
	public String deleteAllUsers() {
		userRepository.deleteAll();
		return "Deleted All Users";
	}

	@Override
	public User findUser(int id) throws UserNotFoundException {
		return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));
	}

	@Override
	public List<User> findAllUsers() {
		List<User> users =  (List<User>) userRepository.findAll();
		return users;
	}

	@Override
	public String updateUser(int id,User user) throws UserNotFoundException {
		Optional<User> existingUser = userRepository.findById(id);
		
		if(existingUser.isPresent()) {
			existingUser.get().setDepartment(user.getDepartment());
			existingUser.get().setFirstName(user.getFirstName());
			existingUser.get().setLastName(user.getLastName());
			existingUser.get().setId(id);
			userRepository.save(existingUser.get());
			return "User updated successfully";
		}else {
			throw new UserNotFoundException(id);
		}
 	}

}
