package gr.unirico.mcflib.exception;

public class IllegalStatusException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public IllegalStatusException() {
		super();
	}

	public IllegalStatusException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalStatusException(String message) {
		super(message);
	}

	public IllegalStatusException(Throwable cause) {
		super(cause);
	}

}
