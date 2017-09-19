package ptit.nhunh.ui;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import lombok.Getter;
import lombok.Setter;
import ptit.nhunh.context.AppContext;
import ptit.nhunh.dao.SQLDAO;
import ptit.nhunh.dao.SQLDAOFactory;
import ptit.nhunh.model.Article;

@ManagedBean
@SessionScoped
public class ArticleUiCtrl implements Serializable {

	private static final long serialVersionUID = 1L;

	private SQLDAO urlDao = SQLDAOFactory.getDAO(SQLDAOFactory.ARTICLE);

	@Getter
	@Setter
	private Article article;

	@Getter
	@Setter
	private boolean hasData = true;

	@Getter
	@Setter
	private String content = "";

	public void init() throws SQLException, FileNotFoundException {
		AppContext appContext = AppContext.getInstance();
		int id = 0;

		if (appContext.getAttribute("article") != null) {
			id = (int) appContext.getAttribute("article");
			this.article = (Article) this.urlDao.findById(id + "");
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						new FileInputStream(this.article.getContentFilePath()), StandardCharsets.UTF_8));
				String line = "";
				while ((line = br.readLine()) != null) {
					this.content = this.content + line;
				}
				Document doc = Jsoup.parse(this.content);
				this.content = doc.text();
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
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