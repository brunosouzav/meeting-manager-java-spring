package challenge.meeting.repositories;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import challenge.meeting.domains.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	
	@Query("SELECT COUNT(r) > 0 FROM Reservation r WHERE r.room.id = :roomId AND " +
            "(r.start < :dataEnd AND r.end > :dataStart)")
	
	boolean findByRoomAndTimeOverlap(@Param("roomId") Long roomId, 
							 @Param("dataStart") LocalDateTime dataStart, 
							@Param("dataEnd")LocalDateTime dataEnd);
	
	@Query("SELECT r FROM Reservation r WHERE r.room.id = : room.id AND DATE(r.dataStart) = : date")
	List<Reservation> findByRoomAndDate(@Param("roomId") Long roomId, @Param("date") LocalDate date);
}
