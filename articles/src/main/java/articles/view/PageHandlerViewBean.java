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
 * BEMERKUNG: der Name ist womoeglich nicht so passend
 */

@Named
@RequestScoped
public class PageHandlerViewBean implements Serializable {
	private static final long serialVersionUID = 3056054091072640427L;
	private String value = "Benutzerkonto erstellen";
    private String outcome = "createAccount";
    private String loginValue = "Anmelden";
    private String loginOutcome = "login";

    @PostConstruct
    public void initialize() {
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        if (session.getAttribute("username") != null) {
            this.value = "Abmelden";
            this.outcome = "logout";
            this.loginValue = (String)session.getAttribute("username");
            this.loginOutcome = "userSettings";
        }
    }

    public String getValue() {
        return this.value;
    }

    public String getOutcome() {
        return this.outcome;
    }

    public String getLoginValue() {
        return this.loginValue;
    }

    public String getLoginOutcome() {
        return this.loginOutcome;
    }

}
