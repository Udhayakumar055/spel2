package com.Spel.SpelLearning.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PropertiesService {
	

	public Map<String, String> map1 = new HashMap<String, String>();
	
	public ArrayList<String> array1 = new ArrayList<String>();
	
	public HashMap<String, String> map2 = new HashMap<String, String>();

	public Map<String, String> getMap1() {
		return map1;
	}

	public void setMap1(Map<String, String> map1) {
		this.map1 = map1;
	}

	public ArrayList<String> getArray1() {
		return array1;
	}

	public void setArray1(ArrayList<String> array1) {
		this.array1 = array1;
	}

	public HashMap<String, String> getMap2() {
		return map2;
	}

	public void setMap2(HashMap<String, String> map2) {
		this.map2 = map2;
	}

	public PropertiesService(Map<String, String> map1, ArrayList<String> array1, HashMap<String, String> map2) {
		super();
		this.map1 = map1;
		this.array1 = array1;
		this.map2 = map2;
	}

}
