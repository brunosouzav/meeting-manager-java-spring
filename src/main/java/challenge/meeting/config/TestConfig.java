package challenge.meeting.config;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import challenge.meeting.domains.Reservation;
import challenge.meeting.domains.Room;
import challenge.meeting.domains.User;
import challenge.meeting.enuns.UserRole;
import challenge.meeting.repositories.ReservationRepository;
import challenge.meeting.repositories.RoomRepository;
import challenge.meeting.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	public UserRepository userRepository;
	
	@Autowired 
	public RoomRepository roomRepository;
	
	@Autowired
	public ReservationRepository reservationRepository;
	
	@Override
	public void run(String... args) throws Exception {
	
		User u1 = new User("Bruno Souza", "bruno@gmail.com", "12345678", UserRole.ADMIN);
		userRepository.save(u1);
	
		Room r1 = new Room( "Meeting", 30);
		roomRepository.save(r1);
		
		int quantity = 20;
		String startStr = "2024-12-11T14:30";
		String endStr = "2024-12-11T15:30";
		
		LocalDateTime dataStart = LocalDateTime.parse(startStr);
		LocalDateTime dataend = LocalDateTime.parse(endStr);
		
		Reservation reservation1 = new Reservation(dataStart, dataend, quantity, null, r1, u1);
		reservationRepository.save(reservation1);
	}

}
