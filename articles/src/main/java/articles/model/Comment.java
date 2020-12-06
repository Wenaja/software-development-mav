package articles.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the comments database table.
 * 
 */
@Entity
@Table(name="comments", schema="k39752uz_articlesdb")
@NamedQuery(name="Comment.findAll", query="SELECT c FROM Comment c")
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "active")
	private Integer active;

	@Column(name = "comment")
	private String comment;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="written_on")
	private Date writtenOn;

	//bi-directional many-to-one association to Category
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="categories_id")
	private Category category = null;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user = null;

	public Comment() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getActive() {
		return this.active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getWrittenOn() {
		return this.writtenOn;
	}

	public void setWrittenOn(Date writtenOn) {
		this.writtenOn = writtenOn;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("Comment [id=");
		builder.append(id);
		builder.append(", active=");
		builder.append(active);
		builder.append(", comment=");
		builder.append(comment);
		builder.append(", writtenOn=");
		builder.append(writtenOn);
		builder.append(", category=");
		builder.append(category);
		builder.append(", user=");
		builder.append(user);
		builder.append("]");
		
		return builder.toString();
	}

}