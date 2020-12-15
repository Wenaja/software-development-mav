package articles.view;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import articles.control.AttSettLogChain;
import articles.control.LoginChain;
import articles.control.StoragerLogChain;
import articles.control.UserFinderLogChain;
import articles.exceptions.LoginFailureException;
import articles.model.User;
import articles.model.manager.StorageManager;

/**
 * @author Juri Rempel
 * @version 1.0
 */
@Named
@RequestScoped
public class UserLoginController implements Serializable {
	private static final long serialVersionUID = -4612346300356170300L;
	private String email;
	private String pwd;

	public UserLoginController() {

	}

	public String makeLogin() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		StorageManager storageManager = new StorageManager();
		UserFinderLogChain log = new UserFinderLogChain(this.email, this.pwd);
		log.setNextChain((LoginChain) new StoragerLogChain(this.email, this.pwd));
		log.getNextChain().setNextChain((LoginChain) new AttSettLogChain(this.email, this.pwd));
		try {
			log.runChainThrough(storageManager, session, new User());
		} catch (LoginFailureException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(),
					"verstehe ich nicht wozu das hier dient"));
			return "login?faces-redirect=false";
		}
		return "index?faces-redirect=true";
	}

	public String getEmail() {
		return this.email;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
