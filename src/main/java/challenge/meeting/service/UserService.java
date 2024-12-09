package challenge.meeting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import challenge.meeting.domains.User;
import challenge.meeting.exception.UserNotFoundException;
import challenge.meeting.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	public UserRepository userRepository;
	
	public void createUser(User user) {
		userRepository.save(user);
	}

	public List<User> findAllUsers (){
		return userRepository.findAll();
	}

	public User findById(Long id) {
		return userRepository.findById(id).orElseThrow(() -> 
				new UserNotFoundException("Usario com id " + id + " não foi encontrado"));
	}
	
	public void updateUser(Long id, User user) {
		User newUser = findById(id);
		
		newUser.setUserName(user.getUserName());
		newUser.setPassword(user.getPassword());
		
		userRepository.save(newUser);
		
		System.out.println("Atualização feita");
	}
}
