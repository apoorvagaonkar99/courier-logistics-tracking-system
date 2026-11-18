package jsp.springbootproject.exceptions;

public class PackageAlreadyExistsException extends RuntimeException {
    public PackageAlreadyExistsException(String message) {
        super(message);
    }

}
