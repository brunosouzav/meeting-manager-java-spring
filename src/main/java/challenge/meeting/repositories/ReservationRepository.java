package challenge.meeting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import challenge.meeting.domains.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
