package articles.model.manager;

import javax.persistence.EntityManager;

import articles.exceptions.NoMatchUserException;
import articles.model.User;

/**
 * @author Juri Rempel
 * @version 1.0
 * 
 */
public class Storager implements Touchable {
	private static final long serialVersionUID = 5352304995772119702L;
	private Integer user_id;
	private String session_id;

	public Storager() {
	}

	public Storager(Integer user_id, String session_id) {
		this.user_id = user_id;
		this.session_id = session_id;
	}

	public User execute(EntityManager em) throws NoMatchUserException {
		User user = (User) em.find(User.class, (Object) this.user_id);
		em.getTransaction().begin();
		user.setSessionID(this.session_id);
		em.getTransaction().commit();
		em.refresh((Object) user);
		em.close();
		return user;
	}

	public void persist(EntityManager em, User user) {
		em.getTransaction().begin();
		em.persist((Object) user);
		em.getTransaction().commit();
		em.close();
	}
}
