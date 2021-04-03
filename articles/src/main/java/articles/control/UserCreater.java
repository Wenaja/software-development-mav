package articles.control;

import java.io.Serializable;

import articles.model.User;
import articles.model.dco.UserTransferObject;

/**
 * @author Juri Rempel
 * @version 1.0
 * 
 */
public class UserCreater implements Serializable {
	private static final long serialVersionUID = -5170843042333678746L;

	public UserCreater() {
		
	}
	
	public User createUser(UserTransferObject transUser) {
		User user = new User();
		user.setActive(Boolean.valueOf(true));
		user.setEmail(transUser.getEmail());
		user.setForename(transUser.getForename());
		user.setSurname(transUser.getSurname());
		user.setUsername(transUser.getUsername());
		return user;
	}
}
