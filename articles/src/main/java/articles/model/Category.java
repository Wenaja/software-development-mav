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
 * The persistent class for the categories database table.
 * 
 */
@Entity
@Table(name="categories", schema="k39752uz_articlesdb")
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "articlecategory")
	private String articlecategory;

	//bi-directional many-to-one association to Comment
	@OneToMany(fetch = FetchType.LAZY, mappedBy="category")
	private List<Comment> comments = null;

	//bi-directional many-to-one association to Title
	@OneToMany(fetch = FetchType.LAZY, mappedBy="category")
	private List<Title> titles = null;

	public Category() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getArticlecategory() {
		return this.articlecategory;
	}

	public void setArticlecategory(String articlecategory) {
		this.articlecategory = articlecategory;
	}

	public List<Comment> getComments() {
		if(comments == null) {
			this.comments = new ArrayList<Comment>();
		}
		
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setCategory(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setCategory(null);

		return comment;
	}

	public List<Title> getTitles() {
		if(titles == null) {
			this.titles = new ArrayList<Title>();
			
		}
		
		return this.titles;
	}

	public void setTitles(List<Title> titles) {
		this.titles = titles;
	}

	public Title addTitle(Title title) {
		getTitles().add(title);
		title.setCategory(this);

		return title;
	}

	public Title removeTitle(Title title) {
		getTitles().remove(title);
		title.setCategory(null);

		return title;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("Category [id=");
		builder.append(id);
		builder.append(", articlecategory=");
		builder.append(articlecategory);
		builder.append("]");
		
		return builder.toString();
	}

}