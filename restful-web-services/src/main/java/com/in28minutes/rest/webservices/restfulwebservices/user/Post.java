package com.in28minutes.rest.webservices.restfulwebservices.user;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Post {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String description;
	
	
	/******************** Explanation of FetchType.Lazy *************************/
	/*******user have many to one relation ship with post*******/
	/*******if post is directly fetch to the user *******/
	/*******so user would try to fetch post and post would try to fetch user *******/
	/*******and this would be kind of recursion *******/
	/*******so this side (at post) we are making it at lazy **********/
	/******so this means unless call, it will not fetch user detail from post******/
	/******unless you call post.getUser() you would not get User info ********/
	@ManyToOne(fetch=FetchType.LAZY)
	private User user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	// we should not select User here in toString method as
	// than **User** will try to print "post" and **Post** will try to print "User"
	// than we will end up out of memory 
	@Override
	public String toString() {
		return "Post [id=" + id + ", description=" + description + "]";
	}

	
	
	
	
}
