package challenge.meeting.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import challenge.meeting.domains.User;
import challenge.meeting.enuns.UserRole;
import challenge.meeting.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	public UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
	
		User u1 = new User(null, "Bruno Souza", "bruno@gmail.com", "12345678", UserRole.ADMIN);
		userRepository.save(u1);
		
	}

}
