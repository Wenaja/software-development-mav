package articles.exceptions;

/**
 * 
 */
public class AccountAlreadyExistException extends Exception {
	private static final long serialVersionUID = 3907528412322175809L;
	public AccountAlreadyExistException(String message) {
        super(message);
    }

}
