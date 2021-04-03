package articles.model.dco;

import articles.model.User;

/**
 * 
 */
public class UserTransferObject extends User {
	private static final long serialVersionUID = -7664162209056484330L;
	private String firstPassword;
	private String secondPassword;

	public void setForename(String forename) {
		super.setForename(forename);
	}

	public String getForename() {
		return super.getForename();
	}

	public void setSurname(String surname) {
		super.setSurname(surname);
	}

	public String getSurname() {
		return super.getSurname();
	}

	public void setUsername(String username) {
		super.setUsername(username);
	}

	public String getUsername() {
		return super.getUsername();
	}

	public void setEmail(String email) {
		super.setEmail(email);
	}

	public String getEmail() {
		return super.getEmail();
	}

	public void setFirstPassword(String firstPassword) {
		this.firstPassword = firstPassword;
	}

	public String getFirstPassword() {
		return this.firstPassword;
	}

	public void setSecondPassword(String secondPassword) {
		this.secondPassword = secondPassword;
		super.setPwd(secondPassword);
	}

	public String getSecondPassword() {
		return this.secondPassword;
	}

}
