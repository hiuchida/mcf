package gr.unirico.mcflib.exception;

public class IllegalPrevidException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public IllegalPrevidException() {
		super();
	}

	public IllegalPrevidException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalPrevidException(String message) {
		super(message);
	}

	public IllegalPrevidException(Throwable cause) {
		super(cause);
	}

}
