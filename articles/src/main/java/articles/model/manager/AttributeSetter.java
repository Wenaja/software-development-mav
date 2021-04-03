package articles.model.manager;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import articles.exceptions.NoMatchUserException;
import articles.model.User;

/**
 * @author Juri Rempel
 * @version 1.0
 * 
 */
public class AttributeSetter implements Touchable {
	private static final long serialVersionUID = -1096259654510139030L;
	private Integer user_id;

	public AttributeSetter(Integer user_id) {
		this.user_id = user_id;
	}

	public User execute(EntityManager em) throws NoMatchUserException {
		User user = (User) em.find(User.class, (Object) this.user_id);
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.setAttribute("user_id", (Object) user.getId());
		session.setAttribute("active", (Object) user.getActive());
		session.setAttribute("email", (Object) user.getEmail());
		session.setAttribute("forename", (Object) user.getForename());
		session.setAttribute("surname", (Object) user.getSurname());
		session.setAttribute("username", (Object) user.getUsername());
		em.close();
		return user;
	}

	public void persist(EntityManager em, User user) {
		em.close();
	}
}
