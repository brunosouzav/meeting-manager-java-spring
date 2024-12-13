package challenge.meeting.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import challenge.meeting.domains.Reservation;
import challenge.meeting.dtos.ReservationRequest;
import challenge.meeting.service.ReservationService;

@RestController
@RequestMapping("/api/reservations")
public class ReservationControllers {

	@Autowired
	public ReservationService reservationService;
	
	@PostMapping
	public ResponseEntity<String> createReservation(@RequestBody ReservationRequest request) {
		
	    reservationService.createReservation(
	        request.roomId(),
	        request.userId(),
	        request.quantity(),
	        request.dataStart(),
	        request.dataEnd()
	    );
	    
	    return ResponseEntity.status(HttpStatus.CREATED)
	                         .body("Reservation created successfully");
	}

	@GetMapping
	public ResponseEntity<List<Reservation>> getAllReservations() {
	    List<Reservation> reservations = reservationService.getAllReservations();
	    return ResponseEntity.ok(reservations);
	
	}
}
