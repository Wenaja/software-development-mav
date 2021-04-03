package articles.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 * @author Juri Rempel
 * @version 1.0
 * 
 */

@Named
@RequestScoped
public class UserSettingsViewBean implements Serializable {
	private static final long serialVersionUID = -3738262074271308794L;
	private String surname = "---";
	private String forename = "---";
	private String username = "---";
	private String email = "---";
	private String numberComments = "---";
	private String accountCreatedOn = "---";

	@PostConstruct
	public void initialize() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		if (session != null) {
			this.surname = (String) session.getAttribute("surname");
			this.forename = (String) session.getAttribute("forename");
			this.username = (String) session.getAttribute("username");
			this.email = (String) session.getAttribute("email");
			this.numberComments = "0";
			this.accountCreatedOn = "01.01.2018";
		}
	}

	public String getSurname() {
		return this.surname;
	}

	public String getForename() {
		return this.forename;
	}

	public String getUsername() {
		return this.username;
	}

	public String getEmail() {
		return this.email;
	}

	public String getNumberComments() {
		return this.numberComments;
	}

	public String getAccountCreatedOn() {
		return this.accountCreatedOn;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNumberComments(String numberComments) {
		this.numberComments = numberComments;
	}

	public void setAccountCreatedOn(String accountCreatedOn) {
		this.accountCreatedOn = accountCreatedOn;
	}
}
