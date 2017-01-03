package com.xiaoconcon.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class CodecUtil {
	private static final Logger log = LoggerFactory.getLogger(CodecUtil.class);

    public static String encodeURL(String url){
    	String target="";
    	try {
			target= URLEncoder.encode(url, "utf-8");
		} catch (UnsupportedEncodingException e) {
			log.error("encode source to utf-8 failure",e);
			throw new RuntimeException();
		}
    	return target;
    }

    public static String decodeURL(String url){
    	String target="";
    	try {
			target= URLDecoder.decode(url, "utf-8");
		} catch (UnsupportedEncodingException e) {
			log.error("decode source to utf-8 failure",e);
			throw new RuntimeException();
		}
    	return target;
    }
}
