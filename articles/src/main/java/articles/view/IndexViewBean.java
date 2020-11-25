package articles.view;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


/**
 * @author Juri Rempel
 * @version 0.2
 */
@Named
@RequestScoped
public class IndexViewBean implements Serializable {
	private static final long serialVersionUID = -561649373995392195L;
	
	public IndexViewBean() {
		
	}
	
	public String showMessage() {
		return "It works!";
	}

}
