package articles.model.manager;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import articles.model.Article;

/**
 * @author Juri Rempel
 * @version 1.0
 */
public class ArticleManager implements Serializable {
	private static final long serialVersionUID = -2473272877474757284L;
	private Articable article;

	public ArticleManager() {

	}

	public ArticleManager(Articable article) {
		this.article = article;
	}

	public void setArticleEntrance(Articable article) {
		this.article = article;
	}

	public List<Article> fillRecords(EntityManager em) {
		return article.fillRecords(em);
	}

}
