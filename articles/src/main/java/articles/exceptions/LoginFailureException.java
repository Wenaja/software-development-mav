package articles.exceptions;

/**
 * @author Juri Rempel
 * @version 1.0
 */
public class LoginFailureException extends Exception {
	private static final long serialVersionUID = 2043955895347697264L;

	public LoginFailureException(String message) {
		super(message);
	}
}
