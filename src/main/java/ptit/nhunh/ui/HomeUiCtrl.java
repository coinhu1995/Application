package ptit.nhunh.ui;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import lombok.Getter;
import lombok.Setter;
import ptit.nhunh.context.AppContext;
import ptit.nhunh.model.Article;
import ptit.nhunh.service.impl.Home_Init_ServiceImpl;

@ManagedBean
@SessionScoped
public class HomeUiCtrl implements Serializable {

	private static final long serialVersionUID = 1L;
		
	@Getter
	@Setter
	private List<Article> listNewArticle;
	
	@Getter
	@Setter
	private List<Article> listTopResponse;
	
//	@Inject
	private Home_Init_ServiceImpl initService = new Home_Init_ServiceImpl();
	
	public void init() throws SQLException {
		AppContext appContext = AppContext.getInstance();
		appContext.setAttribute("item", "trangchu");
		
		this.listNewArticle = this.initService.getNewArticle(5);
		this.listTopResponse = this.initService.getTopResponse(5);
	}
	
	public String articleRedirect(Article article) {
		AppContext appContext = AppContext.getInstance();
		appContext.setAttribute("article", article.getId());
		return "article.xhtml?faces-redirect=true";
	}
}