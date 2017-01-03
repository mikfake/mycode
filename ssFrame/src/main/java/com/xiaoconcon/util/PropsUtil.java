package com.xiaoconcon.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
    * @ClassName: PropsUtil
    * @Description: properties 文件读取工具类
    * @author mike x c Liu
    * @date 2016年10月29日
    *
 */
public final class PropsUtil {
   
	private static final Logger log = LoggerFactory.getLogger(PropsUtil.class);
	
	public static Properties loadProps(String fileName) {
		Properties props = null;
		InputStream is = null;
		try {
	    is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
	    if (null == is) {
	    	throw new FileNotFoundException(fileName+"file is not found");
	    }
	    props = new Properties();
	    props.load(is);
		} catch (IOException e) {
			log.error("load properties file failure",e);
		}finally{
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					log.error("close inputstream failure",e);
				}
			}
		}
		
		return props;
	}
	
	public static String getString(Properties props,String key){
		String value = "";
		try {
			value = props.getProperty(key);	
		} catch (Exception e) {
		}
		return value;
	}
	
	public static int getInt(Properties props,String key){
		int value = 0;
		try {
			value =Integer.parseInt(props.getProperty(key));
		} catch (NumberFormatException e) {
			log.error(key+"value:"+props.getProperty(key)+" is not int",e);
		}
		return value;
	}
	
	public static boolean getBoolean(Properties props,String key){
		boolean value = false;
		try {
			value = Boolean.parseBoolean(props.getProperty(key));
		} catch (Exception e) {
			log.error(key+"value:"+props.getProperty(key)+" is not boolean",e);
		}
		return value;
	}
}
