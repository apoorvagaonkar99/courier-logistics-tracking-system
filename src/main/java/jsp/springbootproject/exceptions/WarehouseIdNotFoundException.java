package jsp.springbootproject.exceptions;

public class WarehouseIdNotFoundException extends RuntimeException{
	public WarehouseIdNotFoundException(String message) {
		super(message);
	}

}
