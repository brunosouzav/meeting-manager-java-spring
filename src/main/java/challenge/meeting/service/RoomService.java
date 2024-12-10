package challenge.meeting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import challenge.meeting.domains.Room;
import challenge.meeting.exception.RoomNotFoundException;
import challenge.meeting.repositories.RoomRepository;

@Service
public class RoomService {
	
	@Autowired
	public RoomRepository roomRepository;
	
	public void createRoom(Room room) {
		roomRepository.save(room);
	}

	public List<Room> getAllRooms(){
		return roomRepository.findAll();
	}

	public Room findById(Long id) {
		return roomRepository.findById(id).orElseThrow(() -> 
		new RoomNotFoundException("Sala com id " + id + " não foi encontrado"));
	}

	public void updateRoom(Long id,  Room room) {
		Room newRoom = findById(id);
		
		newRoom.setName(room.getName());
		newRoom.setMaxCapacity(room.getMaxCapacity());
		
		roomRepository.save(newRoom);
	} 
}
