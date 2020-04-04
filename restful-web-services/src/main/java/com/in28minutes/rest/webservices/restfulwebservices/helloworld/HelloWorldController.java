package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class HelloWorldController {

	//GET
	//URI - /hello-world
	//method - helloWorld();
	@GetMapping(value = "/hello-world")
	public String helloWorld( ) {
		return "Hello Universe";
	}
	
	@GetMapping(value = "/hello-worlds")
	public HelloWorldBean getHelloBean( ) {
		return new HelloWorldBean("Hello World");
	}
	
	@GetMapping(value = "/hello-world/path-variable/{name}")
	public HelloWorldBean getHelloPathVar(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World %s", name));
	}
}
