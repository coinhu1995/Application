package ptit.nhunh.ui;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import lombok.Getter;
import lombok.Setter;
import ptit.nhunh.context.AppContext;

@ManagedBean
@SessionScoped
public class CategoryUiCtrl implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private String item = "asdasd";
		
	
	public void init() {
		AppContext appContext = AppContext.getInstance();
		this.item = (String) appContext.getAttribute("item");
	}
}