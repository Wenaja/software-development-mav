package articles.model.manager;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import articles.model.Article;

/**
 * 
 */
public class StartPageArticleEntrance implements Articable {
	private static final long serialVersionUID = 7929303837973963229L;
	
	public StartPageArticleEntrance() {
		
	}

	@Override
	public List<Article> fillRecords(EntityManager em) {
		TypedQuery<Article> query = em.createQuery("SELECT a FROM Article a", Article.class);
		List<Article> result = query.getResultList();
		ArrayList<Article> articles = new ArrayList<Article>();
		String CATEGORY = "Startseite";
		
		for (Article a : result) {
			String Willkommen = a.getTitle().getCategory().getArticlecategory();
			if (!CATEGORY.equals(Willkommen))
				continue;
			articles.add(a);
		}
		
		if (em.isOpen()) {
			em.close();
		}
		
		return articles;
	}

}
