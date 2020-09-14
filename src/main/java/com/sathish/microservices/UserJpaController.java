package com.sathish.microservices;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//import com.sun.org.apache.xerces.internal.util.URI;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
 
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
@RestController
public class UserJpaController {
	
	@Autowired
	UserDaoService service;
	
	@Autowired
	UserRepository repo;
	
	@GetMapping("/jpa/users")
	public List<User> getAllUser()
	{
		return repo.findAll();
	}
	
	
	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> getUser(@PathVariable int id)
	{
		Optional<User> user =  repo.findById(id);
		if(!user.isPresent())
		{
			throw new UserNotFoundException("User not foun"+id);
		}
		EntityModel<User> rs = EntityModel.of(user.get());
		WebMvcLinkBuilder linkTo = 
				WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).getAllUser());
		
		rs.add(linkTo.withRel("all-users"));
		return rs;
	}
	@DeleteMapping("/jpa/users/{id}")
	public User deleteUser(@PathVariable int id)
	{
		User user =  service.deleteUser(id);
		//System.err.println(user);
		if(user==null)
		{
			throw new UserNotFoundException("User not foun"+id);
		}
		return user;
	}
	
	
	@PostMapping("/jpa/users/user")
	public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {
		User createdUser = service.save(user);
		URI location=ServletUriComponentsBuilder.
		fromCurrentRequest().
		path("/{userId}").
		buildAndExpand(createdUser.getId()).
		toUri();
		return ResponseEntity.created(location).build();
		
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> getAllPost(@PathVariable int id)
	{
		Optional<User> user = repo.findById(id);
		return user.get().getPost();
	}

}
