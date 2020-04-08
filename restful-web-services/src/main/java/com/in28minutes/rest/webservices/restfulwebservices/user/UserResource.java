package com.in28minutes.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.net.URI;
import java.util.Set;

import javax.validation.Valid;

import org.aspectj.weaver.AjAttribute.MethodDeclarationLineNumberAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

//import org.springframework.hateoas.EntityModel;
//import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

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
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		User user = userDaoService.findOne(id);
		if(user == null) {
			throw new UserNotFoundException("id - "+ id);
		}
		//"all-users" SERVER_PATH + "/users"
		//retrieveAllUsers
		EntityModel<User> model = new EntityModel<>(user);
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		model.add(linkTo.withRel("all-users"));
		
		
		//HATEOAS
		return model;
	}
	
	//CREATED
	//input - details of the User
	//output - CREATED & Return The created URI
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {//requestBody actually map to the object and json
		User saveUser = userDaoService.save(user);
		//CREATED CODE
		//ServletUriComponentsBuilder.fromCurrentRequest()   == /user
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()  // this will give us /user
		.path("/{id}")									  // append {id}  now /user/{id}
		.buildAndExpand(saveUser.getId())				  // this will pass the id value 
		.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = userDaoService.delete(id);
		if(user == null) {
			throw new UserNotFoundException("id - "+ id);
		}
	}
}
