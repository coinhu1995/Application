package ptit.nhunh.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

import ptit.nhunh.dao.SQLDAO;
import ptit.nhunh.dao.SQLDAOFactory;
import ptit.nhunh.model.Article;
import ptit.nhunh.service.Home_Init_Service;

@Dependent
@Named
public class Home_Init_ServiceImpl implements Home_Init_Service {
	private SQLDAO urlDao;

	@Override
	public List<Article> getNewArticle(int limit) throws SQLException {
		this.urlDao = SQLDAOFactory.getDAO(SQLDAOFactory.ARTICLE);

		List<Object> listArticle = this.urlDao.getData("select * from TblArticle order by creationTime desc");

		List<Article> list = new ArrayList<>();

		for (int i = 0; i < listArticle.size(); i++) {
			Article article = (Article) listArticle.get(i);
			list.add(article);
		}

		if (limit < list.size()) {
			return list.subList(0, limit);
		} else {
			return list;
		}
	}

	@Override
	public List<Article> getTopResponse(int limit) throws SQLException {
		this.urlDao = SQLDAOFactory.getDAO(SQLDAOFactory.ARTICLE);

		List<Object> listArticle = this.urlDao.getData("select * from TblArticle order by totalParCmt desc");

		List<Article> list = new ArrayList<>();

		for (int i = 0; i < listArticle.size(); i++) {
			Article article = (Article) listArticle.get(i);
			list.add(article);
		}

		if (limit < list.size()) {
			return list.subList(0, limit);
		} else {
			return list;
		}
	}
}
