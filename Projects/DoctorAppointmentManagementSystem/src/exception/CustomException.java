package exception;

public class CustomException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public CustomException(String errorMessage) {
		super(errorMessage);
	}
	
	public CustomException(String errorMessage, Throwable t) {
		super(errorMessage, t);
	}
	
	public CustomException(Throwable t) {
		super(t);
	}
	
	public CustomException() {
		super();
	}
}
