package articles.control;

import javax.servlet.http.HttpSession;

import articles.exceptions.LoginFailureException;
import articles.model.User;
import articles.model.manager.AttributeSetter;
import articles.model.manager.Touchable;

/**
 * @author Juri Rempel
 * @version 1.0
 */
public class AttSettLogChain extends LoginChain {
	private static final long serialVersionUID = -669108241392059170L;
	private String email;
	private String pwd;

	public AttSettLogChain(String email, String pwd) {
		this.email = email;
		this.pwd = pwd;
	}

	public Touchable getTouchable(HttpSession session, User user) throws LoginFailureException {
		if (user.getActive().booleanValue()) {
			return new AttributeSetter(user.getId());
		}
		throw new LoginFailureException("Ihr Benutzerkonto wurde deaktiviert. Mit allen Fragen wenden Sie sich an den Anministrator.");
	}
}
