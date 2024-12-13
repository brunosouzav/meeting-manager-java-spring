package challenge.meeting.domains;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import challenge.meeting.enuns.ReservationStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime start;
	
	@Column(name = "reservation_end", nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime end;
	
	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private ReservationStatus status;
	
	@Column(name = "quantity" , nullable = false)
	private Integer quantity;
	
	@ManyToOne
	@JoinColumn(name = "room_id")
	private Room room ;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	

	   public Reservation(LocalDateTime start, LocalDateTime end, Integer quantity, ReservationStatus status, Room room, User user) {
	        
		   	this.start = start;
	        this.end = end;
	        this.quantity = quantity;
	        this.status = status != null ? status : ReservationStatus.PENDING;  
	        this.room = room;
	        this.user = user;  
	    }
	

}
