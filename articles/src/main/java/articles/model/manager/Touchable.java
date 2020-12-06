package articles.model.manager;

import java.io.Serializable;

import javax.persistence.EntityManager;

import articles.exceptions.NoMatchUserException;
import articles.model.User;

public interface Touchable extends Serializable {
	User execute(EntityManager em) throws NoMatchUserException;
}
