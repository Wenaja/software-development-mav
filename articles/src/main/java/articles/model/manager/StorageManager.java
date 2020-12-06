package articles.model.manager;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import articles.exceptions.LoginFailureException;
import articles.exceptions.NoMatchUserException;
import articles.model.User;

/**
 * @author Juri Rempel
 * @version 1.0
 */
public class StorageManager implements Serializable {
	private static final long serialVersionUID = 5166811668487888402L;
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("net.software-development");

	Touchable storage;

	public StorageManager() {

	}

	public StorageManager(Touchable storage) {
		this.storage = storage;
	}

	public User execute(EntityManager em) throws LoginFailureException {
		User user = null;

		try {
			user = storage.execute(em);

			if (em.isOpen())
				em.close();

			return user;

		} catch (NoMatchUserException e) {
			if (em.isOpen())
				em.close();
			throw new LoginFailureException(e.getMessage());
		}

	}

	public void setNextOperation(Touchable storage) {
		this.storage = storage;
	}

	public static EntityManager getEntityManager() {
		return emf.createEntityManager();

	}

}
