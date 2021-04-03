package articles.view;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpSession;

import articles.model.User;

/**
 * @author Juri Rempel
 * @version 1.0
 * 
 */

@Named
@RequestScoped
public class LogoutViewBean implements Serializable {
	private static final long serialVersionUID = 1500342169403289165L;
	private String username = "---";
	private String email = "---";
	private Long loginTime = 0L;
	private String loginDuration = "---";

	@PostConstruct
	public void initialize() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		if (session != null) {
			this.username = (String) session.getAttribute("username");
			this.email = (String) session.getAttribute("email");
			this.loginTime = session.getCreationTime();
			this.loginDuration = "0";
		}
	}

	public String makeLogout() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory((String) "software-development.net");
		EntityManager em = emf.createEntityManager();
		Integer userId = (Integer) session.getAttribute("user_id");
		User user = (User) em.find(User.class, (Object) userId);
		em.getTransaction().begin();
		user.setSessionID(null);
		em.getTransaction().commit();
		em.close();
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "index?faces-redirect=true";
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoginTime() {
		Date dt = new Date(this.loginTime);
		return dt.toString();
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = Long.parseLong(loginTime);
	}

	public String getLoginDuration() {
		return this.loginDuration;
	}

	public void setLoginDuration(String loginDuration) {
		this.loginDuration = loginDuration;
	}
}
