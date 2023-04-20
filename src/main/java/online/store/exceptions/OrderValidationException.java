package online.store.exceptions;

public class OrderValidationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public OrderValidationException(String message) {
        super(message);
    }
}
