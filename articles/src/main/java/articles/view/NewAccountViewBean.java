package articles.view;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import articles.control.AccountController;
import articles.exceptions.AccountAlreadyExistException;
import articles.exceptions.SQLInjectException;
import articles.model.User;
import articles.model.manager.Beanable;

/**
 * @author Juri Rempel
 * @version 1.0
 * 
 */
public class NewAccountViewBean implements Serializable {
	private static final long serialVersionUID = 1461811335030112420L;
	private String password;
	private User user = new User();

	public String createAccount() {
		String action;
		AccountController account = new AccountController((Beanable) this);
		try {
			action = account.createFor(this.user);
		} catch (AccountAlreadyExistException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
			action = "createAccount?faces-rdirect=false";
		} catch (SQLInjectException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
			action = "createAccount?faces-rdirect=false";
		}
		return action;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
