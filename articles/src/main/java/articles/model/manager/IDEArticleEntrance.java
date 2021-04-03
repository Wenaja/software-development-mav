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
public class IDEArticleEntrance implements Articable {
	private static final long serialVersionUID = -3907079594547691780L;

	public IDEArticleEntrance() {

	}

	@Override
	public List<Article> fillRecords(EntityManager em) {
		TypedQuery<Article> query = em.createQuery("SELECT a FROM Article a", Article.class);
		List<Article> result = query.getResultList();
		ArrayList<Article> articles = new ArrayList<Article>();
		String category = "IDE";
		
		for (Article a : result) {
			String IDE = a.getTitle().getCategory().getArticlecategory();
			if (!category.equals(IDE))
				continue;
			articles.add(a);
		}
		if (em.isOpen()) {
			em.close();
		}
		return articles;
	}

}
