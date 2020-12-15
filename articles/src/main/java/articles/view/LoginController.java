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
			final HttpSession session = this.getSession(this.getExtContext());
			if (!this.isLogged(session)) {
				this.doRedirect("login.jsf");
			}
		} catch (Exception e) {
			this.doRedirect("login.jsf");
		}
	}

	public void verifyUser() {
		try {
			final HttpSession session = this.getSession(this.getExtContext());
			if (this.isLogged(session)) {
				this.doRedirect("userSettings.jsf");
			}
		} catch (Exception e) {
			this.doRedirect("userSettings.jsf");
		}
	}

	private ExternalContext getExtContext() {
		final FacesContext fc = FacesContext.getCurrentInstance();
		return fc.getExternalContext();
	}

	private HttpSession getSession(final ExternalContext ec) throws Exception {
		final HttpSession session = (HttpSession) ec.getSession(false);
		if (session == null) {
			throw new Exception();
		}
		return session;
	}

	private boolean isLogged(final HttpSession session) {
		boolean active = false;
		if (session.getAttribute("active") != null) {
			active = (boolean) session.getAttribute("active");
		}
		return active;
	}

	private void doRedirect(final String url) {
		final ExternalContext extContext = this.getExtContext();
		try {
			extContext.redirect(url);
		} catch (IOException ex) {
		}
	}
}
