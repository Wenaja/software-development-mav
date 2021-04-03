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
	
	@OneToMany(mappedBy="category", fetch=FetchType.LAZY)
    private List<Title> titles;

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

	public List<Title> getTitles() {
		if(this.titles == null) {
			titles = new ArrayList<Title>();
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

	public Title removeArticle(Title title) {
		getTitles().remove(title);
		title.setCategory(null);

		return title;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((articlecategory == null) ? 0 : articlecategory.hashCode());
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
		Category other = (Category) obj;
		if (articlecategory == null) {
			if (other.articlecategory != null)
				return false;
		} else if (!articlecategory.equals(other.articlecategory))
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
		
		builder.append("Category [id=");
		builder.append(id);
		builder.append(", articlecategory=");
		builder.append(articlecategory);
		builder.append("]");
		
		return builder.toString();
	}

}