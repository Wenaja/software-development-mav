package articles.control;

import javax.servlet.http.HttpSession;

import articles.exceptions.LoginFailureException;
import articles.model.User;
import articles.model.manager.Storager;
import articles.model.manager.Touchable;

/**
 * @author Juri Rempel
 * @version 1.0
 * 
 */

public class StoragerLogChain extends LoginChain {
	private static final long serialVersionUID = 4319979392246717939L;

	private String email;
	private String pwd;

	public StoragerLogChain(String email, String pwd) {
		this.email = email;
		this.pwd = pwd;
	}

	@Override
	public Touchable getTouchable(HttpSession session, User user) throws LoginFailureException {
		if (this.pwd.equals(user.getPwd())) {
			return new Storager(user.getId(), session.getId());
		}
		throw new LoginFailureException("Kennwort ist falsch!");
	}
}
