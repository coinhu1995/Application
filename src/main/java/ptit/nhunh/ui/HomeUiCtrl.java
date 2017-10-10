package ptit.nhunh.ui;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
	private RepeatPaginator paginator1;
	
	@Getter
	@Setter
	private RepeatPaginator paginator2;
	
	@Getter
	@Setter
	private List<Article> listNewArticle;

	@Getter
	@Setter
	private List<Article> listTopResponse;

	// @Inject
	private Home_Init_ServiceImpl initService = new Home_Init_ServiceImpl();

	public void init() throws SQLException, IOException {
		AppContext appContext = AppContext.getInstance();
		appContext.setAttribute("item", "trangchu");

		this.listNewArticle = this.initService.getNewArticle(100);
		this.listTopResponse = this.initService.getTopResponse(100);
		
		this.paginator1 = new RepeatPaginator(this.listNewArticle.subList(1, this.listNewArticle.size()));
		this.paginator2 = new RepeatPaginator(this.listTopResponse.subList(1, this.listTopResponse.size()));
	}

	public String articleRedirect(Article article) {
		AppContext appContext = AppContext.getInstance();
		appContext.setAttribute("article", article.getId());
		return "article.xhtml?faces-redirect=true";
	}

	public String getDescription(Article article) throws IOException {
		if (article.getSource().equals("vnexpress.vn")) {
			Document doc = Jsoup.parse(new File("src\\main\\webapp\\" + article.getContentFilePath()), "UTF-8");
			Elements description_cls = doc.getElementsByClass("description");
			if (description_cls.size() > 0) {
				return description_cls.get(0).text();
			}
		} else {
			Document doc = Jsoup.parse(new File("src\\main\\webapp\\" + article.getContentFilePath()), "UTF-8");
			Element chapeau = doc.getElementById("chapeau");
			if (chapeau != null) {
				return chapeau.text();
			}
		}

		return "null";
	}
}