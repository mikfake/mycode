package com.xiaoconcon.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonUtil {
	private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);

	private static final ObjectMapper MAPPER = new ObjectMapper();

	public static <T> String toJson(T obj) {
		String json = "";
		try {
			json = MAPPER.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			log.error("convert to json failure", e);
			throw new RuntimeException();
		}
		return json;
	}

	public static <T> T toPOJO(String json, Class<?> type) {
		T pojo = null;
		try {
			pojo = (T) MAPPER.readValue(json, type);
		} catch (Exception e) {
			log.error("convert json to pojo failure", e);
			throw new RuntimeException();
		}
		return pojo;
	}
}
