package challenge.meeting.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import challenge.meeting.domains.Reservation;
import challenge.meeting.domains.Room;
import challenge.meeting.domains.User;
import challenge.meeting.exception.InvalidReservationException;
import challenge.meeting.exception.ReservationNotFoundException;
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
	
	public boolean checkAvailability(Long roomId, LocalDateTime dataStart, LocalDateTime dataEnd) {
	    boolean isTimeAvailable = !reservationRepository.findByRoomAndTimeOverlap(roomId, dataStart, dataEnd);
	    
	    if(!isTimeAvailable) {
	    	throw new RoomUnavailableException("A sala " + roomId + " não está disponivel nesse horário");
	    }
		
	    return isTimeAvailable;
	}
	
	public void createReservation(Long roomId, Long userId, Integer quantity, String dataStart, String dataEnd) {
		 
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

		    if (dataStart == null || dataEnd == null) {
		        throw new InvalidReservationException("Os horários não podem ser nulos.");
		    }

		    try {
		        LocalDateTime start = LocalDateTime.parse(dataStart, formatter);
		        LocalDateTime end = LocalDateTime.parse(dataEnd, formatter);

		        if (end.isBefore(start)) {
		            throw new InvalidReservationException("A data de término não pode ser anterior à data de início.");
		        }

		       
		        checkAvailability(roomId, start, end);

		        
		        Room room = roomRepository.findById(roomId)
		            .orElseThrow(() -> new RoomNotFoundException("Sala com id " + roomId + " não foi encontrada."));

		       
		        User user = userRepository.findById(userId)
		            .orElseThrow(() -> new UserNotFoundException("Usuário com id " + userId + " não foi encontrado."));
		        
		        if(quantity> room.getMaxCapacity()) {
		        	throw new InvalidReservationException("Capacidade maxima da sala é " + room.getMaxCapacity());
		        	
		        }
		        
		        Reservation reservation = new Reservation(start, end, quantity, null, room, user);

		      
		        reservationRepository.save(reservation);

		    } catch (DateTimeParseException e) {
		        throw new InvalidReservationException("Formato de data inválido. Use o formato yyyy-MM-dd'T'HH:mm:ss.");
		    }
		
	} 

	public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }
	
	public Reservation findByReservation(Long id) {
		return reservationRepository.findById(id).orElseThrow(() -> 
		new ReservationNotFoundException("Reserva N: " + id + " não foi encontrado"));
	}
	
	
	public void updataReservation(Long id, Reservation reservation) {
		
		Reservation newReservation = findByReservation(id);
		newReservation.setStatus(reservation.getStatus());
		
		reservationRepository.save(newReservation);
	}
}
