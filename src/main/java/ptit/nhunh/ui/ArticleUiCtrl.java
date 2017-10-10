package ptit.nhunh.ui;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import lombok.Getter;
import lombok.Setter;
import ptit.nhunh.context.AppContext;
import ptit.nhunh.model.Article;
import ptit.nhunh.model.Comment;
import ptit.nhunh.service.impl.Article_Init_ServiceImpl;

@ManagedBean
@SessionScoped
public class ArticleUiCtrl implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	private RepeatPaginator paginator;
	
	@Getter
	@Setter
	private Article article;

	@Getter
	@Setter
	private List<Comment> listComment;

	@Getter
	@Setter
	private boolean hasData = true;

	private Article_Init_ServiceImpl service = new Article_Init_ServiceImpl();

	public void init() throws SQLException, FileNotFoundException {
		this.listComment = new ArrayList<>();
		AppContext appContext = AppContext.getInstance();
		int id = 0;

		if (appContext.getAttribute("article") != null) {
			id = (int) appContext.getAttribute("article");
			this.article = this.service.getArticle(id + "");
			this.listComment = this.service.getListComment(this.article.getUrl_id());
		} else {
			this.hasData = false;
		}
		
		this.paginator = new RepeatPaginator(this.listComment);
	}

	public String articleRedirect(Article article) {
		AppContext appContext = AppContext.getInstance();
		appContext.setAttribute("article", article.getId());
		return "article.xhtml?faces-redirect=true";
	}
}