package ptit.nhunh.service;

import java.sql.SQLException;
import java.util.List;

import ptit.nhunh.model.Article;

public interface Home_Init_Service {
	public List<Article> getNewArticle(int limit) throws SQLException;
	public List<Article> getTopResponse(int limit) throws SQLException;
}
