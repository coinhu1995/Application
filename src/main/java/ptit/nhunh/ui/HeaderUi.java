package ptit.nhunh.ui;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import lombok.Getter;
import lombok.Setter;
import ptit.nhunh.context.AppContext;
import ptit.nhunh.model.Article;
import ptit.nhunh.service.Header_Init_Service;
import ptit.nhunh.service.impl.Header_Init_ServiceImpl;

@ManagedBean
@SessionScoped
public class HeaderUi implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private List<Article> listThoiSu;

	@Getter
	@Setter
	private List<Article> listKinhDoanh;

	@Getter
	@Setter
	private List<Article> listTheGioi;

	@Getter
	@Setter
	private List<Article> listGiaoDuc;

	@Getter
	@Setter
	private List<Article> listPhapLuat;

	@Getter
	@Setter
	private List<Article> listDoiSong;

	@Getter
	@Setter
	private String item;

	@Inject
	private Header_Init_Service initService;

	public void init() throws SQLException {
		this.initService = new Header_Init_ServiceImpl();
		AppContext appContext = AppContext.getInstance();
		this.item = (String) appContext.getAttribute("item");

		this.listThoiSu = this.initService.getArticle("Thời sự", 5);
		this.listKinhDoanh = this.initService.getArticle("Kinh doanh", 5);
		this.listTheGioi = this.initService.getArticle("Thế giới", 5);
		this.listGiaoDuc = this.initService.getArticle("Giáo dục", 5);
		this.listPhapLuat = this.initService.getArticle("Pháp luật", 5);
		this.listDoiSong = this.initService.getArticle("Đời sống", 5);
	}

	public String getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("EEEEE, MMMMM MM, YYYY");
		Date date = new Date(System.currentTimeMillis());
		return sdf.format(date);
	}

	public String getCurrentItem() {
		AppContext appContext = AppContext.getInstance();
		return (String) appContext.getAttribute("item");
	}

	public String redirect(String item) {
		AppContext appContext = AppContext.getInstance();
		appContext.setAttribute("item", item);
		return "chuyenmuc.xhtml?faces-redirect=true";
	}

	public String articleRedirect(Article article) {
		AppContext appContext = AppContext.getInstance();
		appContext.setAttribute("item", this.item);
		return "article.xhtml?faces-redirect=true";
	}

	public String getTitle(Article article) {
		if (article.getTitle().length() > 72) {
			return article.getTitle() + "...";
		} else {
			return article.getTitle();
		}
	}
}