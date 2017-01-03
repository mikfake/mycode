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
      * @Description: ��ȡbean��ӳ��
      * @param @return    ����
      * @return Map<Class<?>,Object>    ��������
      * @throws
   */
  public static Map<Class<?>,Object> getBeanMap(){
	  return BEAN_MAP;
  }
  /**
   * 
      * @Title: ��ȡbean��ʵ��
      * @Description: TODO(������һ�仰�����������������)
      * @param @param cls
      * @param @return    ����
      * @return T    ��������
      * @throws
   */
  public static <T> T getBean(Class<?> cls){
	  if (!BEAN_MAP.containsKey(cls)) {
		  throw new RuntimeException("can not get bean by class:"+cls);
	  }
	  return (T) BEAN_MAP.get(cls);
  }
}

