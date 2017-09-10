package ptit.nhunh.ui;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ptit.nhunh.context.AppContext;

@ManagedBean
@SessionScoped
public class Home implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public void init() {
		AppContext appContext = AppContext.getInstance();
		appContext.setAttribute("item", "trangchu");
	}
}