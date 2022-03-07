package com.spring.rest.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest.exceptions.UserNotFoundException;
import com.spring.rest.model.User;
import com.spring.rest.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired 
	UserService userService;
	
	@GetMapping("/all")
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> users = userService.findAllUsers();
		return new ResponseEntity<>(users,HttpStatus.OK);
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<User> findUserById(@PathVariable("id") int id){
		User user = null;
		try {
			user = userService.findUser(id);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<User>(user, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> addUser(@RequestBody User user){
		return new ResponseEntity<String>(userService.createUser(user), HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateUser(@PathVariable("id") int id,@RequestBody User user){
		try {
			return new ResponseEntity<String>(userService.updateUser(id, user), HttpStatus.OK);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<String>(e.toString(), HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/deleteAll")
	public ResponseEntity<String> deleteAllUsers(){
		return new ResponseEntity<String>(userService.deleteAllUsers(), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") int id) throws UserNotFoundException{
		return new ResponseEntity<String>(userService.deleteUser(id), HttpStatus.OK);
	}
	
}
