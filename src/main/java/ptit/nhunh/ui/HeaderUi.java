package ptit.nhunh.ui;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import lombok.Getter;
import lombok.Setter;
import ptit.nhunh.context.AppContext;

@ManagedBean
@SessionScoped
public class HeaderUi implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	private String item;
	
	public void init() {
		AppContext appContext = AppContext.getInstance();
		this.item = (String) appContext.getAttribute("item");
	}
	
	public String getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("EEEEE, MMMMM MM, YYYY");
		Date date = new Date(System.currentTimeMillis());
		return sdf.format(date);
	}
	
	public String getCurrentItem() {
		AppContext appContext = AppContext.getInstance();
		return (String) appContext.getAttribute("item");
	}
	
	public String redirect(String item) {
		AppContext appContext = AppContext.getInstance();
		appContext.setAttribute("item", item);
		return "chuyenmuc.xhtml?faces-redirect=true";
	}
}