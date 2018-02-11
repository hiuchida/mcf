package gr.unirico.mcflib.exception;

public class IllegalProofException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public IllegalProofException() {
		super();
	}

	public IllegalProofException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalProofException(String message) {
		super(message);
	}

	public IllegalProofException(Throwable cause) {
		super(cause);
	}

}
