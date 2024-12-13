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

	@GetMapping("/{id}")
	public ResponseEntity<Reservation> findByReservation(@PathVariable Long id){
		Reservation reservation = reservationService.findByReservation(id);
		return ResponseEntity.status(HttpStatus.OK).body(reservation);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateReservation(@PathVariable Long id, @RequestBody Reservation reservation){
		reservationService.updataReservation(id, reservation);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
