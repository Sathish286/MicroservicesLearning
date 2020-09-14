package com.sathish.microservices.FilteringCntroller;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	
	
	@GetMapping("/filter")
	public MappingJacksonValue getBean()
	{
		SomeBean someBean =  new SomeBean("Value1","Value2","Value3");
		
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
		SimpleFilterProvider simpleFilterProvider = new SimpleFilterProvider();
		simpleFilterProvider.addFilter("SomeBeanFilter", filter);
		
		FilterProvider filters = simpleFilterProvider;
	
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		mapping.setFilters(filters);
		
		return mapping;
	}
}
