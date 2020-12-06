package articles.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the articles database table.
 * 
 */
@Entity
@Table(name="articles", schema="k39752uz_articlesdb")
@NamedQuery(name="Article.findAll", query="SELECT a FROM Article a")
public class Article implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "active")
	private Integer active;

	@Column(name = "article")
	private String article;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "published")
	private Date published;

	//bi-directional many-to-one association to Title
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "title")
	private Title titleBean;

	public Article() {
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

	public Title getTitleBean() {
		return this.titleBean;
	}

	public void setTitleBean(Title titleBean) {
		this.titleBean = titleBean;
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
		builder.append(", titleBean=");
		builder.append(titleBean);
		builder.append("]");
		
		return builder.toString();
	}

}