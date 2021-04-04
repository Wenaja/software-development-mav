package articles.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import articles.model.Article;
import articles.model.manager.Articable;
import articles.model.manager.ArticleManager;
import articles.model.manager.IDEArticleEntrance;
import articles.model.manager.JPAArticleEntrance;
import articles.model.manager.JSFArticleEntrance;
import articles.model.manager.StartPageArticleEntrance;

/**
 * @author Juri Rempel
 * @version 1.0
 */
@Named
@RequestScoped
public class HomeContentViewBean implements Serializable {
	private static final long serialVersionUID = -4984741161667403639L;
	private ArticleManager artMan = null;

	@PersistenceContext(unitName = "articles")
	private EntityManager em;

	@PostConstruct
	public void initialize() {
		// StorageManager storMan = new StorageManager();
		this.artMan = new ArticleManager();
	}

	public List<Article> getStartPageArticles() {
		this.artMan.setArticleEntrance((Articable) new StartPageArticleEntrance());
		return this.artMan.fillRecords(/*StorageManager.getEntityManager()*/ em);
	}

	public List<Article> getIDEArticles() {
		this.artMan.setArticleEntrance((Articable) new IDEArticleEntrance());
		return this.artMan.fillRecords(/* StorageManager.getEntityManager()*/ em);
	}

	public List<Article> getJPAArticles() {
		this.artMan.setArticleEntrance((Articable) new JPAArticleEntrance());
		return this.artMan.fillRecords(/*StorageManager.getEntityManager()*/ em);
	}

	public List<Article> getJSFArticles() {
		this.artMan.setArticleEntrance((Articable) new JSFArticleEntrance());
		return this.artMan.fillRecords(/*StorageManager.getEntityManager()*/em);
	}
}
