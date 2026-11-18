package jsp.springbootproject.exceptions;

public class CustomerEmailNotFoundException extends RuntimeException{
	public CustomerEmailNotFoundException(String message) {
		super(message);
	}

}
