package articles.model.manager;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import articles.model.Article;

public interface Articable extends Serializable {
	public List<Article> fillRecords(EntityManager var1);
}
