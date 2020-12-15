package articles.control;

import java.io.Serializable;

/**
 * 
 */
public class SQLInjectInspector implements Serializable {
	private static final long serialVersionUID = -5785387976475986318L;
	private User transUser;

	public SQLInjectInspector(User user) {
		this.transUser = user;
	}

	public boolean hasDetected() {
		boolean erg = true;
		String input = this.transUser.getForename();
		erg = this.isInfected(input.toUpperCase());
		input = this.transUser.getSurname();
		erg = this.isInfected(input.toUpperCase());
		input = this.transUser.getUsername();
		erg = this.isInfected(input.toUpperCase());
		input = this.transUser.getEmail();
		erg = this.isInfected(input.toUpperCase());
		return erg;
	}

	private boolean isInfected(String UPPERfield) {
		boolean infected = true;
		infected = UPPERfield.contains("SELECT") ? true
				: (UPPERfield.contains("UPDATE") ? true
						: (UPPERfield.contains("INSET") ? true : UPPERfield.contains("CREATE")));
		return infected;
	}
}
