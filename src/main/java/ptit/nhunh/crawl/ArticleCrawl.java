package ptit.nhunh.crawl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import ptit.nhunh.dao.SQLDAO;
import ptit.nhunh.dao.SQLDAOFactory;
import ptit.nhunh.model.Article;
import ptit.nhunh.utils.Constants;
import ptit.nhunh.utils.Utils;

public class ArticleCrawl {
	private SQLDAO urlDao;
	private BufferedWriter bw;

	public ArticleCrawl() throws IOException {
		this.urlDao = SQLDAOFactory.getDAO(SQLDAOFactory.ARTICLE);
		this.bw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(new File(Constants.LOG_PATH + "ArticleCrawelLog.txt"), true)));
		this.bw.write(Utils.getCurrentTime() + "\n ------------------------------\n");
	}

	public static void main(String[] args) throws SQLException, IOException {
		new ArticleCrawl().crawlVnExpressArticle();
	}

	public void crawlVnExpressArticle() throws SQLException, IOException {
		List<Object> urls = this.urlDao.getAll();
		String title = "";
		String content = "";
		String category = "";
		for (Object obj : urls) {
			Article url = (Article) obj;
			Document html = Utils.getHtml(url.getUrl());
			if (url.getSource().trim().equals("vnexpress.vn")) {
				try {
					Elements divs = html.getElementsByClass("sidebar_1");
					if (divs.size() > 0) {
						title = html.getElementsByTag("h1").get(0).text();
						url.setTitle(title.trim());
						content = divs.toString();
						if (html.getElementsByClass("start").size() > 0) {
							category = html.getElementsByClass("start").get(0).text().trim();
							url.setCategory(category);
						}
						File f = new File(Constants.DATA_PATH + "article\\" + url.getUrl_id() + ".txt");
						BufferedWriter bw2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));

						bw2.write(content);
						url.setContentFilePath(f.getAbsolutePath());
						if (divs.get(0).getElementsByTag("img").size() > 0) {
							url.setImageUrl(divs.get(0).getElementsByTag("img").get(0).attr("src"));
						} else {
							url.setImageUrl(
									"https://s.vnecdn.net/vnexpress/restruct/i/v46/graphics/img_logo_vne_web.gif");
						}
						this.urlDao.update(url);
						bw2.close();
					}
					System.out.println(url.getId() + " done!");
				} catch (IOException e) {
					this.bw.write(String.format("%-10s", url.getId()) + "\t" + e.getMessage() + "\t" + url.getUrl());
				}
			} else {
				try {
					Elements divs = html.getElementsByClass("content");
					title = html.getElementsByTag("h1").get(0).text();
					if (divs.size() > 0) {
						url.setTitle(title.trim());
						content = divs.toString();
						File f = new File(Constants.DATA_PATH + "article\\" + url.getUrl_id() + ".txt");
						BufferedWriter bw2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));

						bw2.write(content);
						url.setContentFilePath(f.getAbsolutePath());
						if (divs.get(0).getElementsByTag("img").size() > 0) {
							url.setImageUrl(divs.get(0).getElementsByTag("img").get(0).attr("src"));
						} else {
							url.setImageUrl("http://image.thanhnien.vn/v2/App_Themes/images/logo-tn-2.png");
						}
						this.urlDao.update(url);
						bw2.close();
					}
					System.out.println(url.getId() + " done!");
				} catch (IOException e) {
					this.bw.write(String.format("%-10s", url.getId()) + "\t" + e.getMessage() + "\t" + url.getUrl());
				}
			}
		}
	}
}
