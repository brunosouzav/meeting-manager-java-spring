package challenge.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import challenge.meeting.exception.RoomUnavailableException;
import challenge.meeting.repositories.ReservationRepository;
import challenge.meeting.service.ReservationService;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {

	@InjectMocks
	private ReservationService reservationService;
	
	@Mock
	private ReservationRepository reservationRepository;

	@Test
	public void shouldReturnTrueWhenRoomIsAvailable () {
		
		Long roomId = 1L;
		LocalDateTime dataStart = LocalDateTime.of(2024,12,13,10,0);
		LocalDateTime dataEnd = LocalDateTime.of(2024,12,13,11,0);
		
		when(reservationRepository.findByRoomAndTimeOverlap(roomId, dataStart, dataEnd)).thenReturn(false);
	
		boolean isAvailable = reservationService.checkAvailability(roomId, dataStart, dataEnd);
		
		assertTrue(isAvailable, "Sala disponivel");
	}

	public void shouldThrowExceptionWhenRoomIsUnavailable() {
		   Long roomId = 1L;
		    Long userId = 1L;
		    Integer quantity = 20;
		    String dataStart = "2024-12-13T10:00:00";
		    String dataEnd = "2024-12-13T11:00:00";

		    
		    when(reservationRepository.findByRoomAndTimeOverlap(roomId, 
		            LocalDateTime.parse(dataStart, DateTimeFormatter.ISO_LOCAL_DATE_TIME), 
		            LocalDateTime.parse(dataEnd, DateTimeFormatter.ISO_LOCAL_DATE_TIME)))
		        .thenReturn(true);


		    Exception exception = assertThrows(RoomUnavailableException.class, () -> {
		        reservationService.createReservation(roomId, userId, quantity, dataStart, dataEnd);
		    });

		    
		    assertEquals(
		        "Sala 1 indisponível nos horários entre 2024-12-13T10:00:00 e 2024-12-13T11:00:00",
		        exception.getMessage()
		    );

	}
}
