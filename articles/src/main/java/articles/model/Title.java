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

	//bi-directional many-to-one association to Article
	@OneToMany(fetch = FetchType.LAZY, mappedBy="titleBean")
	private List<Article> articles = null;

	//bi-directional many-to-one association to Category
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;

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
		article.setTitleBean(this);

		return article;
	}

	public Article removeArticle(Article article) {
		getArticles().remove(article);
		article.setTitleBean(null);

		return article;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("Title [id=");
		builder.append(id);
		builder.append(", caption=");
		builder.append(caption);
		builder.append(", category=");
		builder.append(category);
		builder.append("]");
		
		return builder.toString();
	}

}