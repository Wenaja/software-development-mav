package articles.control;

import java.io.Serializable;

import articles.exceptions.AccountAlreadyExistException;
import articles.exceptions.NoMatchUserException;
import articles.exceptions.SQLInjectException;
import articles.model.User;
import articles.model.manager.Beanable;
import articles.model.manager.StorageManager;
import articles.model.manager.Storager;
import articles.model.manager.Touchable;
import articles.model.manager.UserFinder;

/**
 * @author Juri Rempel
 * @version 1.0
 * 
 */
public class AccountController implements Serializable {
	private static final long serialVersionUID = -7105625137843736169L;
	//private Beanable newAccontsBean;

	public AccountController(Beanable newAccountsBean) {
		//this.newAccontsBean = newAccountsBean;
		//TODO: wozu hier Beanable?
	}

	public String createFor(User user) throws AccountAlreadyExistException, SQLInjectException {
		UserFinder db = new UserFinder(user.getEmail());
		Storager storageKeeper = new Storager();
		try {
			this.findAccount((Touchable) db);
			this.inspectInput(user);
			user.setActive(new Boolean(true));
			storageKeeper.persist(StorageManager.getEntityManager(), user);
		} catch (AccountAlreadyExistException e) {
			throw new AccountAlreadyExistException(e.getMessage());
		} catch (SQLInjectException e) {
			throw new SQLInjectException(e.getMessage());
		}
		return "NewAccountErgs?faces-redirect=true";
	}

	private void inspectInput(User user) throws SQLInjectException {
		SQLInjectInspector injInspector = new SQLInjectInspector(user);
		if (injInspector.hasDetected()) {
			throw new SQLInjectException("Ung\ufffdltige Zeichen in der Eingabe");
		}
	}

	private boolean findAccount(Touchable db) throws AccountAlreadyExistException {
		User user;
		try {
			user = db.execute(StorageManager.getEntityManager());
		} catch (NoMatchUserException e) {
			return false;
		}
		throw new AccountAlreadyExistException("Email-Adresse " + user.getEmail() + " wird bereits verwendet");
	}
}
