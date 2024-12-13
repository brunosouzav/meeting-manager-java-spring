package challenge.meeting.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

public record ReservationRequest(
		
		Long roomId, 
		Long userId,
		Integer quantity,
		@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")String dataStart, 
		@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")String dataEnd) {


}
