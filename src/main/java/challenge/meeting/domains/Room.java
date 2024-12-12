package challenge.meeting.domains;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "room")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Room {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "room_name", nullable = false, length = 40)
	private String name;
	
	@Column(name = "maxCapacity", nullable = false)
	private Integer maxCapacity;
	
	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
	private Set<Reservation> reservations = new HashSet<>();

	public Room(String name, Integer maxCapacity) {
		super();
		this.name = name;
		this.maxCapacity = maxCapacity;
	}

	
}
