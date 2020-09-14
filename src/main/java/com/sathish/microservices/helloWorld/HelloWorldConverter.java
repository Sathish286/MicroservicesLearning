package com.sathish.microservices.helloWorld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldConverter {
	
	@Autowired
	MessageSource messageSource;
	@GetMapping("/hello-world")
	public String Hello() {
		return "Hello Sathish";
	}
	
	@GetMapping("/hello-world-bean")
	public HelloBean HelloBean() {
		return new HelloBean("from bean");
	}
	
	@GetMapping("/hello-world-bean/path-variable/{name}")
	public HelloBean HelloBean(@PathVariable String name) {
		return new HelloBean(String.format("from bean,%s",name));
	}
	
	@GetMapping(path="/hellow-world-internalization")
	public String internalization(@RequestHeader(name="Accept-Language",required=false) Locale locale) {
		return messageSource.getMessage("good.morning.message", null, locale);
		 
	}


}
