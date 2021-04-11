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
import javax.transaction.UserTransaction;

import org.hibernate.Session;
import org.hibernate.Transaction;

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

	// private SessionContext sessionContext;
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
		if (!em.isOpen()) {
			return "NewAccountFail?faces-redirect=true";
		}

		Session session;
		if (userTransaction != null) {
			System.out.println("cool!");
		}
		Transaction transaction;

		session = em.unwrap(Session.class);

		if (session.isJoinedToTransaction()) {
			System.out.println("session is joined Transaction");
		} else {
			System.out.println("session is NOT joined Transaction");
		}
		/*
		 * session.getTransaction().begin(); if (session.isOpen()) {
		 * System.out.println("session is open"); } else {
		 * System.out.println("session is NOT open"); } if (session.isConnected()) {
		 * System.out.println("session is currently connected"); } else {
		 * System.out.println("session is NOT connected"); } try { transaction =
		 * session.beginTransaction(); if(transaction.isActive())
		 * System.out.print("Transaction is active!"); }catch(Exception exc) {
		 * System.out.println("begin Transaction is fail with the message: " +
		 * exc.getMessage()); } } //userTransaction =
		 * session.unwrap(UserTransaction.class);
		 * 
		 * if (!isUserExist()) { User youngUser = new User();
		 * 
		 * youngUser.setForename(user.getForename());
		 * youngUser.setSurname(user.getSurname());
		 * youngUser.setUsername(user.getUsername());
		 * youngUser.setEmail(user.getEmail()); youngUser.setActive(new Boolean(true));
		 * youngUser.setPwd(user.getSecond_password());
		 * 
		 * em.persist(youngUser); } session.getTransaction().commit();
		 */

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
