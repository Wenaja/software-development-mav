package articles.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the articles database table.
 * 
 */
@Entity
@Table(name = "articles", schema = "k39752uz_articlesdb")
@NamedQuery(name = "Article.findAll", query = "SELECT a FROM Article a")
public class Article implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "active")
	private Boolean active;

	@Column(name = "article")
	private String article;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "published")
	private Date published;

	// bi-directional many-to-one association to Title
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "title")
	private Title title;

	@OneToMany(mappedBy = "article", fetch = FetchType.LAZY)
	private List<Comment> comments;

	public Article() {
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

	public String getArticle() {
		return this.article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public Date getPublished() {
		return this.published;
	}

	public void setPublished(Date published) {
		this.published = published;
	}

	public Title getTitle() {
		return this.title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		if (this.getComments() == null) {
			this.setComments(new ArrayList<Comment>());
		}
		this.getComments().add(comment);
		comment.setArticle(this);
		return comment;
	}

	public Comment removeComment(Comment comment) {
		if (this.getComments() == null) {
			this.setComments(new ArrayList<Comment>());
		}
		this.getComments().remove((Object) comment);
		comment.setArticle(null);
		return comment;
	}

	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + (this.id == null ? 0 : this.id.hashCode());

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}

		Article other = (Article) obj;

		return !(this.id == null ? other.id != null : !this.id.equals(other.id));
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("Article [id=");
		builder.append(id);
		builder.append(", active=");
		builder.append(active);
		builder.append(", article=");
		builder.append(article);
		builder.append(", published=");
		builder.append(published);
		builder.append(", title=");
		builder.append(title);
		builder.append("]");

		return builder.toString();
	}

}