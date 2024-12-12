package challenge.meeting.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import challenge.meeting.domains.Reservation;
import challenge.meeting.domains.Room;
import challenge.meeting.domains.User;
import challenge.meeting.exception.RoomNotFoundException;
import challenge.meeting.exception.RoomUnavailableException;
import challenge.meeting.exception.UserNotFoundException;
import challenge.meeting.repositories.ReservationRepository;
import challenge.meeting.repositories.RoomRepository;
import challenge.meeting.repositories.UserRepository;

@Service
public class ReservationService {

	@Autowired
	public ReservationRepository reservationRepository;
	
	@Autowired 
	public RoomRepository roomRepository;
	
	@Autowired 
	public UserRepository userRepository;
	
	public boolean checkAvailability (Long roomId, LocalDateTime dataStart, LocalDateTime dataEnd) {
		boolean isTimeAvailable = !reservationRepository.findByRoomAndTimeOverlap(roomId, dataStart, dataEnd);
		
		LocalDate dayStart = dataStart.toLocalDate();
		boolean isDayAvailable = reservationRepository.findByRoomAndDate(roomId, dayStart).isEmpty();
		
		return isTimeAvailable && isDayAvailable;
	}
	
	public void createReservation(Long roomId, Long userId, LocalDateTime dataStart, LocalDateTime dataEnd) {
		
		Room room = roomRepository.findById(roomId).orElseThrow(() -> 
		new RoomNotFoundException("Sala com id " + roomId + " não foi encontrado"));
		
		if(!checkAvailability(roomId, dataStart, dataEnd)) {
			throw new RoomUnavailableException("Sala " + roomId + " indisponivel para o dia de hoje ");
					
		}
		
		User user = userRepository.findById(userId).orElseThrow(() -> 
		new UserNotFoundException("Usuario com id " + userId + " não foi encontrado"));
		
		Reservation reservation = new Reservation( dataStart, dataEnd, null, room, user); 
		
		reservationRepository.save(reservation); 
		
		
	} 

}
