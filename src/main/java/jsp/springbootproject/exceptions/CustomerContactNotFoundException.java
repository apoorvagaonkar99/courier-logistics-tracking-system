package jsp.springbootproject.exceptions;


public class CustomerContactNotFoundException extends RuntimeException{
	public CustomerContactNotFoundException(String message) {
		super(message);
	}

}
