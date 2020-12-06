package articles.control;

import javax.servlet.http.HttpSession;

import articles.exceptions.LoginFailureException;
import articles.model.User;
import articles.model.manager.Touchable;
import articles.model.manager.UserFinder;

/**
 * @author Juri Rempel
 * @version 1.0
 */
public class UserFinderLogChain extends LoginChain {
	private static final long serialVersionUID = 2435900642308260746L;
	private String email;
	private String pwd;

	public UserFinderLogChain(String email, String pwd) {
		this.email = email;
		this.pwd = pwd;

	}

	@Override
	public Touchable getTouchable(HttpSession session, User user) throws LoginFailureException {
		if (!email.isEmpty()) {
			return new UserFinder(email);
		}

		throw new LoginFailureException("Bitte, geben Sie Ihre Email-Adresse ein!");
	}

}
