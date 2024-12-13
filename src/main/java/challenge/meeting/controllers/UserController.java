package challenge.meeting.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import challenge.meeting.domains.User;
import challenge.meeting.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired 
	public UserService  userService;
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		userService.createUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}

	@GetMapping
	public ResponseEntity<List<User>> findAllUsers (){
		List<User> users = userService.findAllUsers();
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> findById (@PathVariable Long id){
		User user = userService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> updateUser (@PathVariable Long id, @RequestBody User user){
		userService.updateUser(id, user);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
