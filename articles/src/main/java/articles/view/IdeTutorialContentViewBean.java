package articles.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import articles.model.Article;
import articles.model.manager.ArticleManager;
import articles.model.manager.IDEArticleEntrance;
import articles.model.manager.JPAArticleEntrance;
import articles.model.manager.JSFArticleEntrance;
import articles.model.manager.StorageManager;

/**
 * @author Juri Rempel
 * @version 1.0
 */
@Named
@RequestScoped
public class IdeTutorialContentViewBean implements Serializable {
	private static final long serialVersionUID = -4318433818962250943L;
	private ArticleManager artMan = null;

	public IdeTutorialContentViewBean() {
		
	}

	@PostConstruct
	public void initialize() {
		StorageManager storMan = new StorageManager();
		artMan = new ArticleManager();

	}

	public List<Article> getIDEArticles() {
		artMan.setArticleEntrance(new IDEArticleEntrance());

		return artMan.fillRecords(StorageManager.getEntityManager());
	}

	public List<Article> getJPAArticles() {
		artMan.setArticleEntrance(new JPAArticleEntrance());

		return artMan.fillRecords(StorageManager.getEntityManager());
	}

	public List<Article> getJSFArticles() {
		artMan.setArticleEntrance(new JSFArticleEntrance());

		return artMan.fillRecords(StorageManager.getEntityManager());

	}

}
