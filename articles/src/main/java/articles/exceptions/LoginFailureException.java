package articles.exceptions;

/**
 * 
 */
public class LoginFailureException extends Exception {
	private static final long serialVersionUID = 2043955895347697264L;

	public LoginFailureException(String message) {
		super(message);
	}
}
