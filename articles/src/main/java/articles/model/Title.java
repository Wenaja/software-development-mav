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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the titles database table.
 * 
 */
@Entity
@Table(name="titles", schema="k39752uz_articlesdb")
@NamedQuery(name="Title.findAll", query="SELECT t FROM Title t")
public class Title implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "caption")
	private String caption; 
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;

	//bi-directional many-to-one association to Article
	@OneToMany(fetch = FetchType.LAZY, mappedBy="title")
	private List<Article> articles = null;

	public Title() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCaption() {
		return this.caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}
	
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Article> getArticles() {
		if(articles == null) {
			this.articles = new ArrayList<Article>();		
		}
		
		return this.articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public Article addArticle(Article article) {
		getArticles().add(article);
		article.setTitle(this);

		return article;
	}

	public Article removeArticle(Article article) {
		getArticles().remove(article);
		article.setTitle(null);

		return article;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((caption == null) ? 0 : caption.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Title other = (Title) obj;
		if (caption == null) {
			if (other.caption != null)
				return false;
		} else if (!caption.equals(other.caption))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("Title [id=");
		builder.append(id);
		builder.append(", caption=");
		builder.append(caption);
		builder.append("]");
		
		return builder.toString();
	}

}