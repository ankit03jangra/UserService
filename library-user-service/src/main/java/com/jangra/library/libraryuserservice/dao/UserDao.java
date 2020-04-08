package com.jangra.library.libraryuserservice.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.jangra.library.libraryuserservice.model.User;
import com.jangra.library.libraryuserservice.repository.UserRepository;

@Component
public class UserDao {

	@Autowired
	UserRepository repository;
	
	@Autowired
	private RedisTemplate<String,User> redisTemplate;

	public  ResponseEntity<List<User>> getAllUsers() {
		List<User> users = repository.findAll();
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}

	public ResponseEntity<User> saveUser(User user) {
		  User existingUser=repository.findByEmailId(user.getEmailId()); 
		  if(existingUser==null) { 
			  repository.save(user); 
			  return new ResponseEntity<User>(user, HttpStatus.OK); 
		  }else 
			  throw new ResponseStatusException(HttpStatus.FOUND,user.getEmailId()+" is already registered");
		 }

	public ResponseEntity<User> getUser(User user) {
		String mail = user.getEmailId();
		String key = "user_"+mail;
		ValueOperations<String, User> operations = redisTemplate.opsForValue();
		if(redisTemplate.hasKey(key)) {
			User userFromRedis =operations.get(key);
			return new ResponseEntity<User>(userFromRedis,HttpStatus.OK);
		}
		
		User existingUser = repository.findByEmailId(mail);
		if (existingUser == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, mail+" is not present");
		} else {
			operations.set(key, existingUser);
			System.out.println("From DB");
			return new ResponseEntity<User>(existingUser,HttpStatus.OK);
		}
	}

	public ResponseEntity<User> updateUser(User user) {
		String mail = user.getEmailId();
		String key = "user_"+mail;
		if(redisTemplate.hasKey(key)) {
			redisTemplate.delete(key);
		}

		User existingUser = repository.findByEmailId(mail);
		if (existingUser == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, mail+" is not present");
		} else {
			if(user.getName()!=null)
				existingUser.setName(user.getName());
			if(user.getAge()!=0)
				existingUser.setAge(user.getAge());
			if(user.getIsLibrarian()!=null)
				existingUser.setIsLibrarian(user.getIsLibrarian());
			repository.save(existingUser);
			return new ResponseEntity<User>(existingUser, HttpStatus.OK);
		}
	}

	public ResponseEntity<User> removeUser(User user) {
		String mail = user.getEmailId();
		String key = "user_"+mail;
		if(redisTemplate.hasKey(key)) {
			redisTemplate.delete(key);
		}
		
		User existingUser = repository.findByEmailId(mail);
		if (existingUser == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with this email id is not present");
		} else {
			repository.deleteByEmailId(user.getEmailId());
			return new ResponseEntity<User>(existingUser, HttpStatus.OK);
		}
	}

	public ResponseEntity<User> blockUser(User user) {
		String mail = user.getEmailId();
		String key = "user_"+mail;
		if(redisTemplate.hasKey(key)) {
			redisTemplate.delete(key);
		}
		
		User existingUser = repository.findByEmailId(mail);
		if (existingUser == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, mail+" is not present");
		} else {
			existingUser.setIsActive(false);
			repository.save(existingUser);
			return new ResponseEntity<User>(existingUser,HttpStatus.OK);
		}
	}
 
	public ResponseEntity<User> getUserById(int id) {
		
			Optional<User> user = repository.findById((long)id);
			if(user.isPresent()) {
				return new ResponseEntity<User>(user.get(),HttpStatus.OK);
			}
			else
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User with id " + id + " is not present");
	
	}

}
