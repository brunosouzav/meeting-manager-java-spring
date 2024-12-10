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

import challenge.meeting.domains.Room;
import challenge.meeting.service.RoomService;

@RestController
@RequestMapping("/rooms")
public class RoomController {

	@Autowired
	private RoomService roomService;
	
	@PostMapping
	public ResponseEntity<Room> createRoom (@RequestBody Room room){
		roomService.createRoom(room);
		return ResponseEntity.status(HttpStatus.CREATED).body(room);
	}

	@GetMapping
	public ResponseEntity<List<Room>> getAllRooms(){
		List<Room> rooms = roomService.getAllRooms(); 
		return ResponseEntity.status(HttpStatus.OK).body(rooms);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Room> findByRoom(@PathVariable Long id) {
		Room room = roomService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(room);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> updateRoom(@PathVariable Long id, @RequestBody Room room) {
		roomService.updateRoom(id, room);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
