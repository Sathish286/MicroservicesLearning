package com.sathish.microservices;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

//import org.hibernate.annotations.ManyToAny;

@Entity
public class Post {
	
	@Override
	public String toString() {
		return "Post [id=" + id + ", description=" + description + "]";
	}
	@Id
	@GeneratedValue
	private int id;
	private String description;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private User user;
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public Post(int id, String description, User user) {
		super();
		this.id = id;
		this.description = description;
		this.user = user;
	}
	public Post() {
		super();
	}
	
	
	

}
