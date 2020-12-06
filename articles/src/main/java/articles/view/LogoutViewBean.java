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

	public LogoutViewBean() {

	}

	@PostConstruct
	public void initialize() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

		if (session != null) {
			username = (String) session.getAttribute("username");
			email = (String) session.getAttribute("email");
			loginTime = session.getCreationTime();
			loginDuration = "0";
		}
	}

	public String makeLogout() {

		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("net.software-development");
		EntityManager em = emf.createEntityManager();
		Integer userId = (Integer) session.getAttribute("user_id");
		User user = em.find(User.class, userId);

		em.getTransaction().begin();
		user.setSession(null);
		em.getTransaction().commit();
		em.close();

		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

		return "home?faces-redirect=true";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoginTime() {
		Date dt = new Date(loginTime);
		return dt.toString();
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = Long.parseLong(loginTime);
	}

	public String getLoginDuration() {
		return loginDuration;
	}

	public void setLoginDuration(String loginDuration) {
		this.loginDuration = loginDuration;
	}

}
