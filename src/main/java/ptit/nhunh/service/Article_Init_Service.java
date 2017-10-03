package ptit.nhunh.service;

import java.sql.SQLException;
import java.util.List;

import ptit.nhunh.model.Article;
import ptit.nhunh.model.Comment;

public interface Article_Init_Service {
	public List<Comment> getListComment(String articleId) throws SQLException;
	public Article getArticle(String articleId) throws SQLException;
}
