package ptit.nhunh.ui;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class HeaderUi implements Serializable {

	private static final long serialVersionUID = 1L;

	public String getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("EEEEE, MMMMM MM, YYYY");
		Date date = new Date(System.currentTimeMillis());
		return sdf.format(date);
	}
}