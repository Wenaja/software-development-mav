package articles.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import articles.model.Article;
import articles.model.manager.Articable;
import articles.model.manager.ArticleManager;
import articles.model.manager.IDEArticleEntrance;
import articles.model.manager.JPAArticleEntrance;
import articles.model.manager.JSFArticleEntrance;
import articles.model.manager.StorageManager;

/**
 * @author Juri Rempel
 * @version 1.0
 * 
 */

@Named
@RequestScoped
public class IdeTutorialContentViewBean implements Serializable {
	private static final long serialVersionUID = -4318433818962250943L;
	private ArticleManager artMan = null;

	@PostConstruct
	public void initialize() {
		StorageManager storMan = new StorageManager();
		this.artMan = new ArticleManager();
	}

	public List<Article> getIDEArticles() {
		this.artMan.setArticleEntrance((Articable) new IDEArticleEntrance());
		return this.artMan.fillRecords(StorageManager.getEntityManager());
	}

	public List<Article> getJPAArticles() {
		this.artMan.setArticleEntrance((Articable) new JPAArticleEntrance());
		return this.artMan.fillRecords(StorageManager.getEntityManager());
	}

	public List<Article> getJSFArticles() {
		this.artMan.setArticleEntrance((Articable) new JSFArticleEntrance());
		return this.artMan.fillRecords(StorageManager.getEntityManager());
	}
}
