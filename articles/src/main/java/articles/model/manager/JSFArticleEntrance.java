package articles.model.manager;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import articles.model.Article;

/**
 * @author Juri Rempel
 * @version 1.0
 */
public class JSFArticleEntrance implements Articable {
	private static final long serialVersionUID = 4353078071237658602L;
	
	public JSFArticleEntrance() {
		
	}

	@Override
	public List<Article> fillRecords(EntityManager em) {
		TypedQuery<Article> query = em.createQuery("SELECT a FROM Article a", Article.class);
		List<Article> results = query.getResultList();

		List<Article> articles = new ArrayList<Article>();
		String category = "JSF";
		String JSF;

		for (Article a : results) {
			JSF = a.getCategory().getArticlecategory();

			if (category.equals(JSF)) {
				articles.add(a);
			}
		}

		if (em.isOpen())
			em.close();

		return articles;
	}

}
