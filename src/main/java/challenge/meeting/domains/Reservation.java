package challenge.meeting.domains;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import challenge.meeting.enuns.ReservationStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reservation")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "reservation_start", nullable = false)
	private LocalDateTime start;
	
	@Column(name = "reservation_end", nullable = false)
	private LocalDateTime end;
	
	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private ReservationStatus status;
	
	@ManyToOne
	@JoinColumn(name = "room_id", nullable = false)
	private Room room ;
	
	@ManyToMany
	@JoinTable(name = "tb_user_reservation", joinColumns = 
	@JoinColumn(name = "reservation_id"), inverseJoinColumns = 
	@JoinColumn(name = "user_id"))
	private Set<User> users = new HashSet<>();
}
