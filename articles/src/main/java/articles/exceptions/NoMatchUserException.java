package articles.exceptions;

/**
 * @author Juri Rempel
 * @version 1.0
 */
public class NoMatchUserException extends Exception {
	private static final long serialVersionUID = -6166806562508972520L;

	public NoMatchUserException(String message) {
		super(message);
	}
}
