package com.mapfap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Rice implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public Map<String, String> factorMap;
	private List<String> resultList;
	
	public Rice() {
		factorMap = new HashMap<String, String>();
		resultList = new ArrayList<String>();
	}
	
	public String getFactor(String factor) {
		if (factorMap.containsKey(factor))
			return factorMap.get(factor);
		return "";
	}
	
	public void setFactor(String factor, String value) {
		factorMap.put(factor, value);
	}
	
	public void addResult(String result) {
		resultList.add(result);
	}

	public List<String> getResultList() {
		return resultList;
	}
	
	public boolean check(String factor, String param)
	{
//		System.out.println(factor);
//		return true;
		Set<String> requestConditions = new HashSet<String>();
		for (String x: this.getFactor(factor).split(",")) {
			requestConditions.add(x.trim());
		}
		
		Set<String> availableConditions = new HashSet<String>();
		for (String x: param.split(",")) {
			availableConditions.add(x.trim());
		}
		availableConditions.add("");
		
//		System.out.println(availableConditions);
//		System.out.println(requestConditions);
//		System.out.println(availableConditions.containsAll(requestConditions));
		return availableConditions.containsAll(requestConditions);
	}

}
