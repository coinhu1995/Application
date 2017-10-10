package ptit.nhunh.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ptit.nhunh.dao.SQLDAO;
import ptit.nhunh.dao.SQLDAOFactory;
import ptit.nhunh.model.Article;
import ptit.nhunh.service.Category_Init_Service;

public class Category_Init_ServiceImpl implements Category_Init_Service {

	private SQLDAO urlDao;

	@Override
	public List<Article> getArticle(String item) throws SQLException {
		this.urlDao = SQLDAOFactory.getDAO(SQLDAOFactory.ARTICLE);
		String category = "";
		switch (item) {
		case "congdong":
			category = "Cộng đồng";
			break;
		case "congnghe":
			category = "Công nghệ";
			break;
		case "doisong":
			category = "Đời sống";
			break;
		case "dulich":
			category = "Du lịch";
			break;
		case "giadinh":
			category = "Gia đình";
			break;
		case "giaoduc":
			category = "Giáo dục";
			break;
		case "gioitre":
			category = "Giới trẻ";
			break;
		case "khoahoc":
			category = "Khoa học";
			break;
		case "kinhdoanh":
			category = "Kinh doanh";
			break;
		case "phapluat":
			category = "Pháp luật";
			break;
		case "sohoa":
			category = "Số hóa";
			break;
		case "suckhoe":
			category = "Sức khỏe";
			break;
		case "thegioi":
			category = "Thế giới";
			break;
		case "thethao":
			category = "Thể thao";
			break;
		case "thoisu":
			category = "Thời sự";
			break;
		case "toiviet":
			category = "Tôi viết";
			break;
		case "vanhoa":
			category = "Văn hóa";
			break;
		case "xe":
			category = "Xe";
			break;
		}

		List<Object> listArticle = this.urlDao
				.getData("select * from TblArticle where category = N'" + category + "' order by creationTime desc");

		List<Article> list = new ArrayList<>();

		for (int i = 0; i < listArticle.size(); i++) {
			Article article = (Article) listArticle.get(i);
			list.add(article);
		}

		return list;
	}
}
