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
 */
@Named
@RequestScoped
public class PageController implements Serializable {
	private static final long serialVersionUID = 3056054091072640427L;
	private String value;
	private String outcome;
	private String loginValue;
	private String loginOutcome;

	public PageController() {
		this.value = "Benutzerkonto erstellen";
		this.outcome = "createAccount";
		this.loginValue = "Anmelden";
		this.loginOutcome = "login";
	}

	@PostConstruct
	public void initialize() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

		if (session.getAttribute("username") != null) {
			value = "Abmelden";
			outcome = "logout";
			loginValue = (String) session.getAttribute("username");
			loginOutcome = "userSettings";
		}

	}

	public String getValue() {
		return value;
	}

	public String getOutcome() {
		return outcome;
	}

	public String getLoginValue() {
		return loginValue;
	}

	public String getLoginOutcome() {
		return loginOutcome;
	}

}
