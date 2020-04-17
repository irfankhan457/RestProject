package com.in28minutes.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

			/*********** Twittr use request URI **************
	/***************** URI Versioning  START********************/
	@GetMapping("v1/person")
	public PersonV1 getPersonV1() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping("v2/person")
	public PersonV2 getPersonV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	/***************** URI Versioning END********************/
	
	
			/**** Used By Amazon  request Param ****/
	/***************** Request Param diff START ********************/
	@GetMapping(value="/person/param", params="version=1")
	public PersonV1 getParamV1() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(value="/person/param",  params="version=2")
	public PersonV2 getParamV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	/***************** Request Param diff END ********************/
	
	
				/***** MIcrosoft used Header Versaioning *****/
	/*****************  Header Versionning START ********************/
	@GetMapping(value="/person/header", headers="X-API-VERSION=1")
	public PersonV1 getHeaderV1() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(value="/person/header",  headers="X-API-VERSION=2")
	public PersonV2 getHeaderV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	/***************** Header Versionning END ********************/
	
	
	/******************** Media Type versioning  used in GITHUB *****************/
/**  Media Type versioning aka  Content Negotiation or Accept Header Versionning START **/
	@GetMapping(value="/person/produces", produces="application/vnd.company.app-v1+json")
	public PersonV1 getProduicesV1() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(value="/person/produces",  produces="application/vnd.company.app-v2+json")
	public PersonV2 getProducesV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
/**  Media Type versioning aka  Content Negotiation or Accept Header Versionning END **/
	
}
