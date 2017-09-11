package ptit.nhunh.service;

import java.sql.SQLException;
import java.util.List;

import ptit.nhunh.model.Article;

public interface Header_Init_Service {
	public List<Article> getArticle(String category, int limit) throws SQLException;
}
