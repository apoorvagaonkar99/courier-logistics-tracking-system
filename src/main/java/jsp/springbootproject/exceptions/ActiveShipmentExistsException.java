package jsp.springbootproject.exceptions;

public class ActiveShipmentExistsException extends RuntimeException{
	public ActiveShipmentExistsException(String message) {
		super(message);
	}

}
