package articles.model.manager;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import articles.exceptions.NoMatchUserException;
import articles.model.User;

/**
 * @author Juri Rempel
 * @version 1.0
 */
public class AttributeSetter implements Touchable {
	private static final long serialVersionUID = -1096259654510139030L;
	private Integer user_id;

	public AttributeSetter(Integer user_id) {
		this.user_id = user_id;
	}

	@Override
	public User execute(EntityManager em) throws NoMatchUserException {
		User user = em.find(User.class, user_id);
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

		session.setAttribute("user_id", user.getId());
		session.setAttribute("active", user.getActive());
		session.setAttribute("email", user.getEmail());
		session.setAttribute("forename", user.getForename());
		session.setAttribute("surname", user.getSurname());
		session.setAttribute("username", user.getUsername());

		em.close();

		return user;
	}

}
