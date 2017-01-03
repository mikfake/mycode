package com.xiaoconcon.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ReflectionUtil {
   private static final Logger log = LoggerFactory.getLogger(ReflectionUtil.class);
   /**
    * 
       * @Title: newInstance
       * @Description: 创建实例
       * @param @param cls
       * @param @return    参数
       * @return Object    返回类型
       * @throws
    */
   public static Object newInstance (Class<?> cls){
	   Object instance = null;
	   try {
		instance = cls.newInstance();
	} catch (Exception e) {
		log.error("new instance failure",e);
		throw new RuntimeException(e);
	} 
	   return instance;
   }
   /**
    * 
       * @Title: invokeMethod
       * @Description: 调用方法
       * @param @param obj
       * @param @param method
       * @param @param args
       * @param @return    参数
       * @return Object    返回类型
       * @throws
    */
   public static Object invokeMethod(Object obj, Method method,Object ...args){
	   Object result =null;
	   try {
		method.setAccessible(true);
		result = method.invoke(obj, args);
	} catch (Exception e) {
		log.error("invoke method failure",e);
		throw new RuntimeException(e);
	}
	   return result;
   }
   
   public static void setField(Object obj,Field field,Object value){
	   field.setAccessible(true);
	   try {
		field.set(obj, value);
	} catch (Exception e) {
		log.error("set field failure",e);
		throw new RuntimeException(e);
	} 
   }
}
