package articles.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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
	@Resource(name = "java:comp/env/jdbc/articlesDB", authenticationType = javax.annotation.Resource.AuthenticationType.CONTAINER, type = javax.sql.DataSource.class)
	private UserTransaction userTransaction;
	// private org.apache.tomcat.dbcp.dbcp2.BasicDataSource dataSource;

	public NewAccountController() {

	}

	@PostConstruct
	private void initilize() {
		user = new UserCompositeObject();
		/*
		 * this.emf = Persistence.createEntityManagerFactory("articles"); em =
		 * emf.createEntityManager();
		 */
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
		System.out.print("foo ===> ");

		if (em.isOpen()) {
			System.out.println("em is open");
		} else {
			System.out.println("em is close");
		}

		try {
			if (userTransaction == null) {
				System.out.println("foo ==> userTransaction is NULL");
				EntityTransaction et = em.getTransaction();
				if(et != null) {
					if(et.isActive()) {
						System.out.print("EntityTransaction not NULL and active!");
					}else {
						System.out.print("EntityTransaction not NULL but no active :(");
					}
				}
				et.begin();
				et.rollback();
				return "NewAccountFail?faces-redirect=true";
			} else {
				userTransaction.begin();
			}
			
			if (!isUserExist()) {
				User youngUser = new User();

				youngUser.setForename(user.getForename());
				youngUser.setSurname(user.getSurname());
				youngUser.setUsername(user.getUsername());
				youngUser.setEmail(user.getEmail());
				youngUser.setActive(new Boolean(true));
				youngUser.setPwd(user.getSecond_password());

				em.persist(youngUser);

			}

			userTransaction.commit();

		} catch (SecurityException | IllegalStateException | NotSupportedException | SystemException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			try {
				userTransaction.rollback();

			} catch (IllegalStateException | SecurityException | SystemException e1) {
				e1.printStackTrace();

			}

			e.printStackTrace();

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
