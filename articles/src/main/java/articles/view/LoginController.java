package articles.view;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 * @author Juri Rempel
 * @author 1.0
 */
@Named
@RequestScoped
public class LoginController implements Serializable {
	private static final long serialVersionUID = 5795758240108533110L;

	public LoginController() {

	}

	public void verifyLogin() {
		try {
			HttpSession session = getSession(getExtContext());
			if (!isLogged(session)) {
				doRedirect("login.jsf");
			}
		} catch (Exception e) {
			doRedirect("login.jsf");
		}
	}

	public void verifyUser() {
		try {
			HttpSession session = getSession(getExtContext());
			if (isLogged(session)) {
				doRedirect("userSettings.jsf");
			}
		} catch (Exception e) {
			doRedirect("userSettings.jsf");
		}
	}

	private ExternalContext getExtContext() {
		FacesContext fc = FacesContext.getCurrentInstance();

		return fc.getExternalContext();
	}

	private HttpSession getSession(ExternalContext ec) throws Exception {
		HttpSession session = (HttpSession) ec.getSession(false);

		if (session == null) {
			throw new Exception();
		}

		return session;
	}

	private boolean isLogged(HttpSession session) {
		boolean active = false;

		if (session.getAttribute("active") != null) {
			active = (boolean) session.getAttribute("active");
		}

		return active;
	}

	private void doRedirect(String url) {
		ExternalContext extContext = getExtContext();

		try {
			extContext.redirect(url);
		} catch (IOException e) {
			// missing function
		}
	}

}
