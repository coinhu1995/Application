package ptit.nhunh.context;

import java.util.HashMap;
import java.util.Map;

public class AppContext {
	private static final AppContext instance = new AppContext();

	private Map<String, Object> map;

	private AppContext() {
		this.map = new HashMap<String, Object>();
	}

	public static AppContext getInstance() {
		return AppContext.instance;
	}
	
	public void setAttribute(String key, Object value) {
		this.map.put(key, value);
	}

	public Object getAttribute(String key) {
		return this.map.get(key);
	}
}
