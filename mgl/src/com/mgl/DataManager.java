package com.mgl;

import java.util.HashMap;
import java.util.Map;

public class DataManager {
	
	private static final DataManager instance = new DataManager();
	
	private DataManager(){}
	
	private Map<String, Data> data = new HashMap<String, Data>();

	public static DataManager getInstance() {
		return instance;
	}
		
	public Map<String, Data> getData() {
		return data;
	}
	public Data getData(String key) {
		return data.get(key);
	}

	public void setData(Map<String, Data> data) {
		this.data = data;
	}
	public void setData(String key, Data data) {
		this.data.put(key, data);
	}

	
	
}
