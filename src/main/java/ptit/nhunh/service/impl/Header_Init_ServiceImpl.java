package ptit.nhunh.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

import ptit.nhunh.dao.SQLDAO;
import ptit.nhunh.dao.SQLDAOFactory;
import ptit.nhunh.model.Article;
import ptit.nhunh.service.Header_Init_Service;

@Dependent
@Named
public class Header_Init_ServiceImpl implements Header_Init_Service {

	private SQLDAO urlDao = SQLDAOFactory.getDAO(SQLDAOFactory.ARTICLE);

	@Override
	public List<Article> getArticle(String category, int limit) throws SQLException {
		List<Object> list = this.urlDao
				.getData("select * from TblArticle where category = N'" + category + "' order by totalParCmt");
		List<Article> listArticle = new ArrayList<>();

		for (int i = 0; i < list.size(); i++) {
			listArticle.add((Article) list.get(i));
		}

		if (limit < listArticle.size()) {
			return listArticle.subList(0, limit);
		} else {
			return listArticle;
		}
	}
}
