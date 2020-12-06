package articles.control;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

import articles.exceptions.LoginFailureException;
import articles.model.User;
import articles.model.manager.StorageManager;
import articles.model.manager.Touchable;

/**
 * @author Juri Rempel
 * @version 1.0
 */
public abstract class LoginChain implements Serializable {
	private static final long serialVersionUID = 3191814935382515362L;
	private LoginChain next = null;

	public void setNextChain(LoginChain next) {
		this.next = next;
	}

	public LoginChain getNextChain() {
		return next;
	}

	public void runChainThrough(StorageManager storageManager, HttpSession session, User user)
			throws LoginFailureException {
		storageManager.setNextOperation(getTouchable(session, user));
		user = storageManager.execute(StorageManager.getEntityManager());

		if (next != null) {
			next.runChainThrough(storageManager, session, user);
		}

	}

	abstract Touchable getTouchable(HttpSession session, User user) throws LoginFailureException;
}
