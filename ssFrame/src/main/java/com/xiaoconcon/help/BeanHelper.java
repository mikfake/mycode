package com.xiaoconcon.help;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.xiaoconcon.util.ReflectionUtil;

public final class BeanHelper {
  private static final Map<Class<?>,Object> BEAN_MAP = new HashMap<Class<?>,Object>();
  static {
	  Set<Class<?>> set = ClassHelper.getBeanClassSet();
	  for (Class<?> cls : set) {
		Object obj = ReflectionUtil.newInstance(cls);
		BEAN_MAP.put(cls, obj);
	}
  }
  /**
   * 
      * @Title: getBeanMap
      * @Description: 获取bean的映射
      * @param @return    参数
      * @return Map<Class<?>,Object>    返回类型
      * @throws
   */
  public static Map<Class<?>,Object> getBeanMap(){
	  return BEAN_MAP;
  }
  /**
   * 
      * @Title: 获取bean的实例
      * @Description: TODO(这里用一句话描述这个方法的作用)
      * @param @param cls
      * @param @return    参数
      * @return T    返回类型
      * @throws
   */
  public static <T> T getBean(Class<?> cls){
	  if (!BEAN_MAP.containsKey(cls)) {
		  throw new RuntimeException("can not get bean by class:"+cls);
	  }
	  return (T) BEAN_MAP.get(cls);
  }
}

