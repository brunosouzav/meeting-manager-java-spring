package challenge.meeting.exception;

public class RoomUnavailableException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public RoomUnavailableException (String message) {
		super(message);
	}

}
