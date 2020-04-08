package com.jangra.library.libraryuserservice.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jangra.library.libraryuserservice.dao.UserDao;
import com.jangra.library.libraryuserservice.model.User;

@RestController
@RequestMapping("/LibraryManagement")
public class UserController {
	
	@Autowired
	UserDao dao;	

	/*
	 * @RequestMapping("/test") public String test() { System.out.println("Test");
	 * return "Test services"; }
	 */
	
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<User>> getAllUsers(){
		return dao.getAllUsers();
	}
	
	@PostMapping("/saveUser")
	public ResponseEntity<User> saveUser(@RequestBody @Valid User user) {
		return dao.saveUser(user);	
	}
	
	@GetMapping("/getUserById/{id}")
	public ResponseEntity<User> getUserById(@PathVariable @Valid int id) {
		return dao.getUserById(id);
	}
	
	@PostMapping("/getUser")
	public ResponseEntity<User> getUser(@RequestBody User user) {
		return dao.getUser(user);
	}
	
	@PutMapping("/updateUser")
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		return dao.updateUser(user);
	}
	
	@DeleteMapping("/removeUser")
	public ResponseEntity<User> removeUser(@RequestBody User user) {
		return dao.removeUser(user);
	}
	
	@PutMapping("/blockUser")
	public ResponseEntity<User> blockUser(@RequestBody User user) {
		return dao.blockUser(user);
	}
		
}