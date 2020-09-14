package com.sathish.microservices.helloWorld;

public class HelloBean {
	
	
	
	private String message;
	
	public HelloBean(String message)
	{
		this.message=message;
	}

	

	public String getMessage() {
		System.out.println("inside getMessage");
		return message;
	}

	public void setMessage(String message) {
		System.out.println("insise set message");
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "HelloBean [message=" + message + "]";
	}

}
