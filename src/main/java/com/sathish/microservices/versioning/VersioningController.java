package com.sathish.microservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningController {
	
	
	@GetMapping("v1/person")
	public Personv1 personv1() {
		
		return new Personv1("sathish r");
	}
	
	@GetMapping("v2/person")
	public Personv2 personv2()
	{
		return new Personv2(new Name("sathish","r"));
	}
	
	
	@GetMapping(value="person/param",params="version=1")
	public Personv1 personParam1()
	{
		return new Personv1("sathish r");
	}
	
	@GetMapping(value="person/param",params="version=2")
	public Personv2 personParam2()
	{
		return new Personv2(new Name("sathish","r"));
	}
	
	@GetMapping(value="/person/header",headers="X-API-VERSION=1")
	public Personv1 personv1Header()
	{
		return new Personv1("sath r");
	}
	
	@GetMapping(value="/person/header",headers="X-API-VERSION=2")
	public Personv2 personv2Header() {
		return new Personv2(new Name("sat","sa"));
	}
	
	@GetMapping(value="/person/produces",produces="application/app-v1+json")
	public Personv1 personv1Produces() {
		return new Personv1("sathish r");
	}
	@GetMapping(value="/person/produces",produces="application/app-v2+json")
	public Personv2 personv2Produces()
	{
		return new Personv2(new Name("sa","sa"));
	}

}
