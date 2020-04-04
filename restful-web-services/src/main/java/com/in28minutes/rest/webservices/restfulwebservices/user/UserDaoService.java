package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	private static Set<User> users = new HashSet<User>();
	
	private static int userCount = 4;
	
	static {
		users.add(new User(1, "adam", new Date()));
		users.add(new User(2, "eve", new Date()));
		users.add(new User(3, "jack", new Date()));
		users.add(new User(4, "jill", new Date()));
	}
	
	public Set<User> findAll() {
		 return users;
	}
	
	public User save(User user) {
		if(user.getId() == null) {
			user.setId(++userCount);
		}
		users.add(user);
		return user;		
	}
	public User findOne(int id) {
		for(User user : users) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}
}
