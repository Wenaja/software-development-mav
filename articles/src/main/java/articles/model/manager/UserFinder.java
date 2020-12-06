package articles.model.manager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import articles.exceptions.NoMatchUserException;
import articles.model.User;

/**
 * @author Rempel
 * @version 1.0
 */
public class UserFinder implements Touchable {
	private static final long serialVersionUID = 8273866281522418937L;
	private String email;

	public UserFinder(String email) {
		this.email = email;

	}

	@Override
	public User execute(EntityManager em) throws NoMatchUserException {
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
		List<User> users = query.getResultList();

		for (User user : users) {
			if (email.equals(user.getEmail())) {
				em.close();
				return user;
			}
		}

		throw new NoMatchUserException("Es wurde keinen Benutzer mit der Email-Adresse " + email + " gefunden");
	}

}
