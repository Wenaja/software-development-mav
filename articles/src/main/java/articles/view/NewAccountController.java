package articles.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import articles.model.User;
import articles.model.dco.UserCompositeObject;

/**
 * @author Juri Rempel
 * @version 1.0
 */
@Named
@RequestScoped
public class NewAccountController implements Serializable {
	private static final long serialVersionUID = -6172552035246225005L;
	private UserCompositeObject user = null;
	// private EntityManagerFactory emf;

	@PersistenceContext(unitName = "articles")
	private EntityManager em;
	@Resource(name = "jdbc/articlesDB", authenticationType = javax.annotation.Resource.AuthenticationType.CONTAINER, type = org.apache.tomcat.dbcp.dbcp2.BasicDataSource.class)
	//private UserTransaction userTransaction;
	private org.apache.tomcat.dbcp.dbcp2.BasicDataSource dataSource;

	public NewAccountController() {

	}

	@PostConstruct
	private void initilize() {
		user = new UserCompositeObject();
		/*
		 * this.emf = Persistence.createEntityManagerFactory("articles"); em =
		 * emf.createEntityManager();
		 */
		dataSource.getConnection();
	}

	public UserCompositeObject getUser() {
		return user;
	}

	public void setUser(UserCompositeObject user) {
		this.user = user;
	}

	private boolean isUserExist() {
		TypedQuery<String> query = em.createQuery("SELECT u.email FROM User u", String.class);

		List<String> ls = query.getResultList();

		for (String email : ls) {
			if (email.equals(user.getEmail())) {
				return true;
			}
		}

		return false;
	}

	public String foo() {
		try {
			em.joinTransaction();
			userTransaction.begin();

			if (!isUserExist()) {

				User youngUser = new User();

				youngUser.setForename(user.getForename());
				youngUser.setSurname(user.getSurname());
				youngUser.setUsername(user.getUsername());
				youngUser.setEmail(user.getEmail());
				youngUser.setActive(new Boolean(true));
				youngUser.setPwd(user.getSecond_password());

				em.persist(youngUser);
				userTransaction.commit();

				return "NewAccountErgs?faces-redirect=true";

			}

		} catch (SecurityException | IllegalStateException | NotSupportedException | SystemException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			try {
				em.getTransaction().rollback();
			} catch (IllegalStateException | SecurityException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return "NewAccountFail?faces-redirect=true";

		}

		return "NewAccountFail?faces-redirect=true";

	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("Aus dem Formular in Server angekommen:  [Vorname=");
		if (user != null) {
			builder.append(user.getForename());
			builder.append(", Nachname=");
			builder.append(user.getSurname());
			builder.append(", Benutzername=");
			builder.append(user.getUsername());
			builder.append(", Email=");
			builder.append(user.getEmail());
		}
		builder.append("]");

		return builder.toString();
	}

}
