package ptit.nhunh.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ptit.nhunh.dao.SQLDAO;
import ptit.nhunh.dao.SQLDAOFactory;
import ptit.nhunh.model.Article;
import ptit.nhunh.model.Comment;
import ptit.nhunh.service.Article_Init_Service;

public class Article_Init_ServiceImpl implements Article_Init_Service {
	private SQLDAO urlDao = SQLDAOFactory.getDAO(SQLDAOFactory.ARTICLE);
	private SQLDAO cmtDao = SQLDAOFactory.getDAO(SQLDAOFactory.COMMENT);

	@Override
	public List<Comment> getListComment(String articleId) throws SQLException {
		List<Comment> listComment = new ArrayList<>();
		List<Object> listObjCmt = this.cmtDao.getData("select * from TblComment where page_id = '" + articleId + "'");

		for (int i = 0; i < listObjCmt.size(); i++) {
			listComment.add((Comment) listObjCmt.get(i));
		}
		return listComment;
	}

	@Override
	public Article getArticle(String articleId) throws SQLException {
		return (Article) this.urlDao.findById(articleId + "");
	}

}
