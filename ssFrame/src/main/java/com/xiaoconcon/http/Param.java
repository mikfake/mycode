package com.xiaoconcon.http;

import java.util.Map;

public class Param {
	private Map<String, Object> paraMap;

	public Param(Map<String, Object> paraMap) {
		super();
		this.paraMap = paraMap;
	}

	public Map<String, Object> getParaMap() {
		return paraMap;
	}

	public void setParaMap(Map<String, Object> paraMap) {
		this.paraMap = paraMap;
	}

	public long getLong(String name) {
		return Long.parseLong((String) paraMap.get(name));
	}
}
