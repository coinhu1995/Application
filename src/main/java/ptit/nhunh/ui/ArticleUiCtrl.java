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
import ptit.nhunh.dao.SQLDAO;
import ptit.nhunh.dao.SQLDAOFactory;
import ptit.nhunh.model.Article;
import ptit.nhunh.model.Comment;

@ManagedBean
@SessionScoped
public class ArticleUiCtrl implements Serializable {

	private static final long serialVersionUID = 1L;

	private SQLDAO urlDao = SQLDAOFactory.getDAO(SQLDAOFactory.ARTICLE);
	private SQLDAO cmtDao = SQLDAOFactory.getDAO(SQLDAOFactory.COMMENT);

	@Getter
	@Setter
	private Article article;

	@Getter
	@Setter
	private List<Comment> listComment;

	@Getter
	@Setter
	private boolean hasData = true;

	public void init() throws SQLException, FileNotFoundException {
		this.listComment = new ArrayList<>();
		AppContext appContext = AppContext.getInstance();
		int id = 0;

		if (appContext.getAttribute("article") != null) {
			id = (int) appContext.getAttribute("article");
			this.article = (Article) this.urlDao.findById(id + "");

			List<Object> listObjCmt = this.cmtDao
					.getData("select * from TblComment where page_id = '" + this.article.getUrl_id() + "'");

			for (int i = 0; i < listObjCmt.size(); i++) {
				this.listComment.add((Comment) listObjCmt.get(i));
			}
		} else {
			this.hasData = false;
		}
	}

	public String articleRedirect(Article article) {
		AppContext appContext = AppContext.getInstance();
		appContext.setAttribute("article", article.getId());
		return "article.xhtml?faces-redirect=true";
	}
}