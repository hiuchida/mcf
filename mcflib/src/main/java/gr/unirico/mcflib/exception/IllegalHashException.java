package gr.unirico.mcflib.exception;

public class IllegalHashException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public IllegalHashException() {
		super();
	}

	public IllegalHashException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalHashException(String message) {
		super(message);
	}

	public IllegalHashException(Throwable cause) {
		super(cause);
	}

}
