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
import ptit.nhunh.service.impl.Category_Init_ServiceImpl;

@ManagedBean
@SessionScoped
public class CategoryUiCtrl implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private RepeatPaginator paginator;

	@Getter
	@Setter
	private String item = "";

	@Getter
	@Setter
	private List<Article> listArticle;

	private Category_Init_ServiceImpl service = new Category_Init_ServiceImpl();

	public void init() throws SQLException {
		AppContext appContext = AppContext.getInstance();
		this.item = (String) appContext.getAttribute("item");

		this.listArticle = this.service.getArticle(this.item);

		this.paginator = new RepeatPaginator(this.listArticle.subList(1, this.listArticle.size()));
	}

	public String articleRedirect(Article article) {
		AppContext appContext = AppContext.getInstance();
		appContext.setAttribute("item", this.item);
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