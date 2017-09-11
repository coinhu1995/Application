package ptit.nhunh.crawl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import ptit.nhunh.dao.SQLDAO;
import ptit.nhunh.dao.SQLDAOFactory;
import ptit.nhunh.model.Article;
import ptit.nhunh.utils.Utils;

public class CategoryCrawl {
	public static void main(String[] args) throws SQLException {
		SQLDAO urlDao = SQLDAOFactory.getDAO(SQLDAOFactory.URL);
		List<Object> listUrl = urlDao.getAll();

		for (int i = 0; i < listUrl.size(); i++) {
			Article url = (Article) listUrl.get(i);
			if (url.getSource().equals("vnexpress.vn")) {
				Document doc;
				try {
					doc = Utils.getHtml(url.getUrl());

					Elements li_s = doc.getElementsByClass("start");
					if (li_s.size() > 0) {
						((Article) listUrl.get(i)).setCategory(li_s.get(0).text().trim());
					}

					Elements span_s = doc.getElementsByClass("time");
					if (span_s.size() > 0) {
						((Article) listUrl.get(i)).setCreationTime(span_s.get(0).text().trim());
					}

					urlDao.update(listUrl.get(i));
					System.out.println(((Article) listUrl.get(i)).getId() + " done!");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else {
				Document doc;
				try {
					doc = Utils.getHtml(url.getUrl());

					Elements li_s = doc.getElementsByClass("sub");
					if (li_s.size() > 0) {
						url.setCategory(li_s.get(0).text().trim());
					}

					Elements time_s = doc.getElementsByTag("time");
					if (time_s.size() > 0) {
						url.setCreationTime(time_s.get(0).text().trim());
					}

					urlDao.update(listUrl.get(i));
					System.out.println(((Article) listUrl.get(i)).getId() + " done!");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}