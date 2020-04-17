package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringConfiguration {

	//field1 , field2
	@GetMapping("/filtering")
	public MappingJacksonValue retrieveSomeBean() {
		SomeBean sBean = new SomeBean("value1", "value2", "value3");
		
/***************** Dynamic Filtering added by Irfan Start *********************/
		//Filtering Property Added Start: Irfan
		SimpleBeanPropertyFilter sBeanPropFilter = SimpleBeanPropertyFilter
				.filterOutAllExcept("field1", "field2");
		FilterProvider filterProvider = new SimpleFilterProvider()
				.addFilter("someBeanFilter",sBeanPropFilter);
		//Filtering Property Added End: Irfan
		
		//Mapping and Set Filter provider Start: Irfan
		MappingJacksonValue mappingJackValue = new MappingJacksonValue(sBean);
		mappingJackValue.setFilters(filterProvider);
		//Mapping and Set Filter provider End: Irfan
/***************** Dynamic Filtering added by Irfan End *********************/
		
		return mappingJackValue;
	}
	
	//filed 2, filed 3
	@GetMapping("/filtering-list")
	public MappingJacksonValue retrieveListOfSomeBean() {
		//Alt+Shift+L
		List<SomeBean> asList = Arrays.asList(new SomeBean("value1", "value2", "value3"), 
				new SomeBean("value12", "value22", "value32"));
		
/***************** Dynamic Filtering added by Irfan Start *********************/
		//Filtering Property Added Start: Irfan
		SimpleBeanPropertyFilter sBeanPropFilter = SimpleBeanPropertyFilter
				.filterOutAllExcept("field2", "field3");
		FilterProvider filterProvider = new SimpleFilterProvider()
				.addFilter("someBeanFilter",sBeanPropFilter);
		//Filtering Property Added End: Irfan
		
		//Mapping and Set Filter provider Start: Irfan
		MappingJacksonValue mappingJackValue = new MappingJacksonValue(asList);
		mappingJackValue.setFilters(filterProvider);
		//Mapping and Set Filter provider End: Irfan
/***************** Dynamic Filtering added by Irfan End *********************/
		
		
		return mappingJackValue;
	}
	
	
}
