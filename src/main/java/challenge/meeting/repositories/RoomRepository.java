package challenge.meeting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import challenge.meeting.domains.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

}
