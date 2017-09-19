package test;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import ptit.nhunh.dao.SQLDAO;
import ptit.nhunh.dao.SQLDAOFactory;
import ptit.nhunh.model.Article;

public class test {
	public static void main(String[] args) throws IOException, SQLException, ParseException {
		BufferedWriter bw = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream("temp.txt"), StandardCharsets.UTF_8));
		SQLDAO articleDao = SQLDAOFactory.getDAO(SQLDAOFactory.ARTICLE);
		List<Object> listArt = articleDao.getAll();
		for (Object obj : listArt) {
			Article article = (Article) obj;
			bw.write(article.getId());
			bw.newLine();
			bw.write(article.getUrl());
			bw.newLine();
			bw.write(article.getUrl_id());
			bw.newLine();
			bw.write(article.getTitle());
			bw.newLine();
			bw.write(article.getNeeded());
			bw.newLine();
			bw.write(article.getSource());
			bw.newLine();
			bw.write(article.getTotalComment());
			bw.newLine();
			bw.write(article.getTotalParComment());
			bw.newLine();
			bw.write(article.getTag());
			bw.newLine();
			if (article.getCategory() != null) {
				bw.write(article.getCategory());
			} else {
				bw.write(" ");
			}
			bw.newLine();
			if (article.getCategory() != null) {
				bw.write(article.getCreationTime().toString());
			} else {
				bw.write(" ");
			}
			bw.newLine();
			if (article.getCategory() != null) {
				bw.write(article.getContentFilePath());
			} else {
				bw.write(" ");
			}
			bw.newLine();
			if (article.getCategory() != null) {
				bw.write(article.getImageUrl());
			} else {
				bw.write(" ");
			}
			bw.newLine();
		}
		bw.close();
	}
}
