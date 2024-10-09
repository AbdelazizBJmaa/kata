package exception;

public class NonSufficientFundsException extends Exception {

	public NonSufficientFundsException() {
		super();
	}

	public NonSufficientFundsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NonSufficientFundsException(String message, Throwable cause) {
		super(message, cause);
	}

	public NonSufficientFundsException(String message) {
		super(message);
	}

	public NonSufficientFundsException(Throwable cause) {
		super(cause);
	}

}
