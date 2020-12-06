package articles.model.dco;

import java.io.Serializable;

/**
 * @author Juri Rempel
 * @version 1.0
 */
public class UserCompositeObject implements Serializable {
	private static final long serialVersionUID = -7132830371496777366L;
	private String forename;
	private String surname;
	private String username;
	private String email;
	private String first_password;
	private String second_password;

	public UserCompositeObject() {

	}

	public String getForename() {
		return forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirst_password() {
		return first_password;
	}

	public String getSecond_password() {
		return second_password;
	}

	public void setFirst_password(String first_password) {
		this.first_password = first_password;
	}

	public void setSecond_password(String second_password) {
		this.second_password = second_password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
