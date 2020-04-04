package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService userDaoService;

	//GET /users
	//retrieve All User
	@GetMapping("/users")
	public Set<User> retrieveAllUsers() {
		return userDaoService.findAll();
	}
	
	//GET /users/{id}
	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		User user = userDaoService.findOne(id);
		if(user == null) {
			throw new UserNotFoundException("id - "+ id);
		}
		return user;
	}
	
	//CREATED
	//input - details of the User
	//output - CREATED & Return The created URI
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@RequestBody User user) {//requestBody actually map to the object and json
		User saveUser = userDaoService.save(user);
		//CREATED CODE
		//ServletUriComponentsBuilder.fromCurrentRequest()   == /user
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()  // this will give us /user
		.path("/{id}")									  // append {id}  now /user/{id}
		.buildAndExpand(saveUser.getId())				  // this will pass the id value 
		.toUri();
		return ResponseEntity.created(location).build();
	}
}
