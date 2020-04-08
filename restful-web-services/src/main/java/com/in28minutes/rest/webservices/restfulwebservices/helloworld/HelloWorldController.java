package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;

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
	
	@GetMapping(value = "/hello-world-intrnationalized")
	public String helloWorldInternationalized(@RequestHeader(name="Accept-Language", 
	required=false) Locale locale) {
		String result = messageSource.getMessage("good.morning.message", null, locale);
		return result;
	}
}
