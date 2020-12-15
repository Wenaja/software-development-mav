package articles.exceptions;

/**
 * 
 */
public class SQLInjectException extends Exception {
	private static final long serialVersionUID = -1576638675274317236L;

	public SQLInjectException(String message) {
		super(message);
	}
}
