package articles.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name = "users", schema = "k39752uz_articlesdb")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "active")
	private Boolean active;

	@Column(name = "email")
	private String email;

	@Column(name = "forename")
	private String forename;

	@Column(name = "surname")
	private String surname;

	@Column(name = "username")
	private String username;

	@Column(name = "pwd")
	private String pwd;

	@Column(name = "sessionid")
	private String sessionID;

	// bi-directional many-to-one association to Comment
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<Comment> comments = null;

	public User() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getForename() {
		return this.forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public List<Comment> getComments() {
		if (comments == null) {
			this.comments = new ArrayList<Comment>();
		}

		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setUser(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setUser(null);

		return comment;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean first = false, second = false;
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		User other = (User) obj;
		
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email)) {
			return false;
		}else {
			first = true;
		}
		
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)) {
			return false;
		}else {
			second = true;
		}
		
		if(first && second) {
			return true;
		}
		
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("User [id=");
		builder.append(id);
		builder.append(", active=");
		builder.append(active);
		builder.append(", email=");
		builder.append(email);
		builder.append(", forename=");
		builder.append(forename);
		builder.append(", pwd=");
		builder.append(pwd);
		builder.append(", sessionID=");
		builder.append(sessionID);
		builder.append(", surname=");
		builder.append(surname);
		builder.append(", username=");
		builder.append(username);
		builder.append("]");

		return builder.toString();

	}

}