package com.sm.site.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import com.sm.site.controller.exceptionshandler.UserNotFoundException;
import com.sm.site.post.Post;
import com.sm.site.postrepo.PostRepository;
import com.sm.site.repo.UserRepo;
import com.sm.site.user.User;

import jakarta.validation.Valid;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private final UserRepo repo;
	
	@Autowired
	private final PostRepository prepo;

	public UserController(UserRepo repo, PostRepository prepo) {
		this.repo = repo;
		this.prepo = prepo;
	}

	@GetMapping("all")
	public ResponseEntity<List<User>> getAll() {

		List<User> all = repo.findAll();

		if (all.isEmpty()) {

			throw new UserNotFoundException("No se han registrado usuarios");
		}

		return new ResponseEntity<>(all, HttpStatus.FOUND);

	}

	@GetMapping("find{id}")
	public ResponseEntity<User> findById(@PathVariable("id") Integer id) {

		Optional<User> user0 = repo.findById(id);

		if (user0.isEmpty()) {
			throw new UserNotFoundException("Usuario no existente");
		}

		return new ResponseEntity<>(user0.get(), HttpStatus.OK);
	}

	@PostMapping("/createUser")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user, Integer id) throws Exception {

		boolean exist = repo.existByEmail(user.getEmail());

		if (exist) {

			throw new Exception("Email is already registered, try logging in.");
		}

		User user1 = repo.save(user);

		return new ResponseEntity<>(user1, HttpStatus.ACCEPTED);
	}

	@PutMapping("/editUser/{id}")
	public ResponseEntity<User> editUser(@RequestBody User user, @PathVariable("id") Integer id) throws Exception {

		boolean exist = repo.existByEmail(user.getEmail());

		if (repo.existsById(id) == false) {

			throw new UserNotFoundException("User does not exist, try a different one.");

		}

		if (exist == true || exist == false && user.getEmail() != user.getEmail()) {

			throw new UserNotFoundException("Email is already taken.");

		}

		User user2 = repo.save(user);

		return new ResponseEntity<>(user2, HttpStatus.ACCEPTED);

	}

	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity <?> deleteUser(User user, @PathVariable ("id")Integer id){
		
		Optional <User> existe = repo.findById(id);
		
		if (existe.isEmpty()) {
			
			throw new UserNotFoundException("Usuario no existe");
		}
		
		     repo.deleteById(id);
		
		
		return new ResponseEntity <> ("User with ID: " + id + " successfully deleted. ", HttpStatus.ACCEPTED);
		
	}
	
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@PostMapping("/postfor/{id}")
	public ResponseEntity <Object> createPost(@RequestBody Post post, @PathVariable ("id") Integer id){
		
		Optional <User> user= repo.findById(id);
		
		if (user.isEmpty()) {
			throw new UserNotFoundException("Usuario" + id + "No existe..." + "Intenta creando tu usuario antes de poster");
		}
		
		 post.setUser(user.get());
		 Post savePost = prepo.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savePost.getId()
				).toUri();	
		
		
		return new ResponseEntity <> (location, HttpStatus.ACCEPTED);
		
		
	}
	
	@GetMapping("/getpostFor/{id}")
	public ResponseEntity <List<Post>> getPost( @PathVariable ("id")Integer id){
		
		Optional <User> user1 = repo.findById(id);
		
		if (user1.isEmpty()) {
			
			throw new UserNotFoundException("Usuario no existe");
			
		} else if (user1.toString().isBlank()) {
			
			
			throw new UserNotFoundException("Usuario no tiene post, crea un post");
			
		}
		     
		return new ResponseEntity <> (user1.get().getPost(), HttpStatus.OK);
		
		
	}
	
	
	@PutMapping("/editpost/{id}")
	public ResponseEntity <Object> editPost(@RequestBody Post post, @PathVariable ("id") Integer id){
		
		Optional <User> user = repo.findById(id);
            
		if(user.isEmpty()) {
			
			throw new UserNotFoundException("Usuario no existe");
			
		} 
		   post.setUser(user.get());
		   Post edited =  prepo.save(post);
		
		   return new ResponseEntity <> (edited, HttpStatus.ACCEPTED);
	}	
	
	
	
	
}
