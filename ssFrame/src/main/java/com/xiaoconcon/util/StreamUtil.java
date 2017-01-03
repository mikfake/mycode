package com.xiaoconcon.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class StreamUtil {
	private static final Logger log = LoggerFactory.getLogger(StreamUtil.class);

	public static String getString(InputStream is) {
		String line = "";
		StringBuffer sb = new StringBuffer();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		try {
			while ((line = br.readLine()) != null) {
				sb.append(br.readLine());
			}
		} catch (IOException e) {
			log.error("read from inputstream failure", e);
			throw new RuntimeException();
		}
		return sb.toString();
	}

}
